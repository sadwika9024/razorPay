package com.pabbasadwika.razorPay.payment.dto.request;

import com.pabbasadwika.razorPay.common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateOrderRequest(
        @NotNull(message = "Amount is required")
        Money amount,

        //receipt is orderId which is known to the merchant
        @Size(max = 100)
        String receipt,

        //user phn or address or itemId -- it comes as json

        Map<String,Object> notes,

        LocalDateTime expiresAt
) {
}
