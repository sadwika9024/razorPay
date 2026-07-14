package com.pabbasadwika.razorPay.payment.service.impl;

import com.pabbasadwika.razorPay.common.enums.OrderStatus;
import com.pabbasadwika.razorPay.common.exception.BusinessRuleValidationException;
import com.pabbasadwika.razorPay.common.exception.DuplicateResourceException;
import com.pabbasadwika.razorPay.common.exception.ResourceNotFound;
import com.pabbasadwika.razorPay.payment.dto.request.CreateOrderRequest;
import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.dto.response.PaymentResponse;
import com.pabbasadwika.razorPay.payment.entity.OrderRecord;
import com.pabbasadwika.razorPay.payment.entity.Payment;
import com.pabbasadwika.razorPay.payment.mapper.PaymentMapper;
import com.pabbasadwika.razorPay.payment.repository.OrderRespository;
import com.pabbasadwika.razorPay.payment.repository.PaymentRepository;
import com.pabbasadwika.razorPay.payment.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {



    private final OrderRespository orderRespository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    @Transactional
    public OrderResponse create(UUID merchantId, CreateOrderRequest request) {
        if(request.receipt()!=null && orderRespository.existsByMerchantIdAndReceipt(merchantId,request.receipt())){
            throw new DuplicateResourceException("ORDER_RECEIPT_DUPLICATE","Order with receipt already exists:"+request.receipt());
        }

        OrderRecord orderRecord = OrderRecord
                .builder()
                .amount(request.amount())
                .notes(request.notes())
                .receipt(request.receipt())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expireAt(request.expiresAt()!=null ? request.expiresAt() :
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMinutes))
                .build();


        orderRecord = orderRespository.save(orderRecord);

        //TODO : publish kafka event about order creation , idempotency key

        return new OrderResponse(
                orderRecord.getId(),
                orderRecord.getMerchantId(),
                orderRecord.getReceipt(),
                orderRecord.getAmount(),
                orderRecord.getOrderStatus(),
                orderRecord.getAttempts(),
                orderRecord.getNotes(),
                orderRecord.getExpireAt(),
                null
        );
    }

    @Override
    public OrderResponse getById(UUID merchantId, UUID orderID) {
        OrderRecord order =  orderRespository.findByIdAndMerchantId(orderID,merchantId)
                .orElseThrow(()-> new ResourceNotFound("order",orderID));

        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrderStatus(),
                order.getAttempts(),
                order.getNotes(),
                order.getExpireAt(),
                null
        );

    }

    @Override
    @Transactional
    public OrderResponse cancel(UUID merchantId, UUID orderID) {

        OrderRecord order =  orderRespository.findByIdAndMerchantId(orderID,merchantId)
                .orElseThrow(()-> new ResourceNotFound("order",orderID));

        if(order.getOrderStatus() == OrderStatus.CANCELLED || order.getOrderStatus()==OrderStatus.PAID){
            throw new BusinessRuleValidationException("ORDER_CANNOT_CANCEL","cannot cancel order with status"+order.getOrderStatus().name());
        }


        order.setOrderStatus(OrderStatus.CANCELLED);

        order = orderRespository.save(order);

        return new OrderResponse(
                order.getId(),
                order.getMerchantId(),
                order.getReceipt(),
                order.getAmount(),
                order.getOrderStatus(),
                order.getAttempts(),
                order.getNotes(),
                order.getExpireAt(),
                null
        );

    }

    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderID) {
        OrderRecord order = orderRespository.findByIdAndMerchantId(orderID,merchantId)
                .orElseThrow(()-> new ResourceNotFound("order",orderID));

        List<Payment> paymentList = paymentRepository.findByOrder_id(order);
        return paymentList.stream()
                .map(payment -> paymentMapper.toResponse(payment))
                .collect(Collectors.toList());
    }
}
