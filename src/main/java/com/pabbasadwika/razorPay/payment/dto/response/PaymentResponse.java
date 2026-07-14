package com.pabbasadwika.razorPay.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pabbasadwika.razorPay.common.entity.Money;
import com.pabbasadwika.razorPay.common.enums.PaymentMethod;
import com.pabbasadwika.razorPay.common.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

//it will only send the fields which are notnull
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(

        UUID id,
        UUID orderId,
        UUID merchantId,
        Money money,
        PaymentStatus status,
        PaymentMethod method,
        Map<String,Object> methodDetails,
        String cardLastFour,
        String cardBrand,
        String bankReference,
        String errorCode,
        String errorDescription,
        Long refundedAmountPaise,
        LocalDateTime capturedAt,
        LocalDateTime createdAt

) {
}
