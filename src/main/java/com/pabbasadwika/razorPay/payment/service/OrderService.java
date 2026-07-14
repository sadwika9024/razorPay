package com.pabbasadwika.razorPay.payment.service;

import com.pabbasadwika.razorPay.payment.dto.request.CreateOrderRequest;
import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
