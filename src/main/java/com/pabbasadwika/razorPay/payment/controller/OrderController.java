package com.pabbasadwika.razorPay.payment.controller;

import com.pabbasadwika.razorPay.payment.dto.request.CreateOrderRequest;
import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    UUID merchantId = UUID.fromString("f0773469-3c4a-4272-a111-12414d8b0caa");//TODO: replace it with merchant context


    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(merchantId,request));

    }


}
