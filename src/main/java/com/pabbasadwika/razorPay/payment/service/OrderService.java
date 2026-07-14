package com.pabbasadwika.razorPay.payment.service;

import com.pabbasadwika.razorPay.payment.dto.request.CreateOrderRequest;
import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.dto.response.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);

    OrderResponse getById(UUID merchantId,UUID orderID);

    OrderResponse cancel(UUID merchantId,UUID orderID);

    List<PaymentResponse> listPayments(UUID merchantId,UUID orderID);
}
