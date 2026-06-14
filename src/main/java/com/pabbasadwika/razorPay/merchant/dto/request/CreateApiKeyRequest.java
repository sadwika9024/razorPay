package com.pabbasadwika.razorPay.merchant.dto.request;

import com.pabbasadwika.razorPay.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment

) {
}
