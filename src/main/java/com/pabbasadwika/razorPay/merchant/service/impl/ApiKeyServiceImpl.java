package com.pabbasadwika.razorPay.merchant.service.impl;

import com.pabbasadwika.razorPay.common.exception.ResourceNotFound;
import com.pabbasadwika.razorPay.merchant.dto.request.ApiKeyCreateResponse;
import com.pabbasadwika.razorPay.merchant.dto.request.CreateApiKeyRequest;
import com.pabbasadwika.razorPay.merchant.entity.ApiKey;
import com.pabbasadwika.razorPay.merchant.entity.Merchant;
import com.pabbasadwika.razorPay.merchant.repository.ApiKeyRepository;
import com.pabbasadwika.razorPay.merchant.repository.MerchantRepository;
import com.pabbasadwika.razorPay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFound("merchant",merchantId));
        String KeyID = "rzp_"+request.environment().name().toUpperCase()+"big_random_string";
        String rawSecret = "big_random_Secret";//TODO : replace with cryptographic random hex
        //a-z,A-Z,0-9,-,_

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(KeyID)
                .keySecretHash(rawSecret) //TODO : encode with BycryptPassword
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);
        return new ApiKeyCreateResponse(
                apiKey.getId(),
                apiKey.getKeyId(),
                rawSecret,
                apiKey.getEnvironment()
        );
    }
}
