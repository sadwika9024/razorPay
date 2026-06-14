package com.pabbasadwika.razorPay.merchant.dto.request;

import com.pabbasadwika.razorPay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String KeyId,
        String KeySecret,
        Environment environment
) {
}
