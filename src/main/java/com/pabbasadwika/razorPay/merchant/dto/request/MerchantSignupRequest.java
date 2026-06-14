package com.pabbasadwika.razorPay.merchant.dto.request;

import com.pabbasadwika.razorPay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignupRequest(

        @NotNull(message = "name should be provided")
        @Size(max = 50 , message = "name length should be less than 50")
        String name,


        @Email(message = "email pattern is not satisfied")
        @NotNull(message = "email is required")
        String email,

        @NotNull(message = "password is required")
        @Size(min = 8 , message = "password should be min of 8 characters long")
        String password,

        @Size(max = 50,message = "businessname cannot be more than 50 characters")
        String businessName,

        BusinessType businessType


) {
}
