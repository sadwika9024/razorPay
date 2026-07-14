package com.pabbasadwika.razorPay.payment.service.impl;

import com.pabbasadwika.razorPay.common.enums.OrderStatus;
import com.pabbasadwika.razorPay.common.exception.DuplicateResourceException;
import com.pabbasadwika.razorPay.payment.dto.request.CreateOrderRequest;
import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.entity.OrderRecord;
import com.pabbasadwika.razorPay.payment.repository.OrderRespository;
import com.pabbasadwika.razorPay.payment.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {



    private final OrderRespository orderRespository;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
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
}
