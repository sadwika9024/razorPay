package com.pabbasadwika.razorPay.merchant.service;

import com.pabbasadwika.razorPay.merchant.dto.request.MerchantSignupRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.MerchantResponse;

public interface AuthService {
    MerchantResponse signup(MerchantSignupRequest request);
}
