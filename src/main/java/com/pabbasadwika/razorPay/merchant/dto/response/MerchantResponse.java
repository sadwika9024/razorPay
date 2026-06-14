package com.pabbasadwika.razorPay.merchant.dto.response;

import com.pabbasadwika.razorPay.common.enums.BusinessType;
import com.pabbasadwika.razorPay.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(

        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
