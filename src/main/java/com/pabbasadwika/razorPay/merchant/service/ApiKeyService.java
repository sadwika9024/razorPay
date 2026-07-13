package com.pabbasadwika.razorPay.merchant.service;

import com.pabbasadwika.razorPay.merchant.dto.request.ApiKeyCreateResponse;
import com.pabbasadwika.razorPay.merchant.dto.request.CreateApiKeyRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.ApiKeyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request);

    List<ApiKeyResponse> list(UUID merchantID);

    void revoke(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotateKey(UUID merchantId, UUID keyId);
}
