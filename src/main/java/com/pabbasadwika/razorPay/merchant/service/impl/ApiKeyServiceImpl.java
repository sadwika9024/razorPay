package com.pabbasadwika.razorPay.merchant.service.impl;

import com.pabbasadwika.razorPay.common.exception.ResourceNotFound;
import com.pabbasadwika.razorPay.common.util.RandomizerUtil;
import com.pabbasadwika.razorPay.merchant.dto.request.ApiKeyCreateResponse;
import com.pabbasadwika.razorPay.merchant.dto.request.CreateApiKeyRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.ApiKeyResponse;
import com.pabbasadwika.razorPay.merchant.entity.ApiKey;
import com.pabbasadwika.razorPay.merchant.entity.Merchant;
import com.pabbasadwika.razorPay.merchant.mapper.ApiKeyMapper;
import com.pabbasadwika.razorPay.merchant.mapper.MerchantMapper;
import com.pabbasadwika.razorPay.merchant.repository.ApiKeyRepository;
import com.pabbasadwika.razorPay.merchant.repository.MerchantRepository;
import com.pabbasadwika.razorPay.merchant.service.ApiKeyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;

    private final ApiKeyRepository apiKeyRepository;
    private final ApiKeyMapper apiKeyMapper;

    /* what is an apikey -- to communicate between merchant to razorpay
    to connect :  its simple username is keyId and password is keysecret*/
    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFound("merchant",merchantId));
        String KeyID = "rzp_"+request.environment().name().toLowerCase()+"_"+RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);//TODO : replace with cryptographic random hex
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

    @Override
    public List<ApiKeyResponse> list(UUID merchantID) {
        return apiKeyMapper.toListOfApiKeyResponse(apiKeyRepository.findByMerchant_Id(merchantID));
    }

    //revoke means deleting -- if enabled as false means deleting..
    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID ApiKeyId) {
    // we are checking if the keyid is present and its available for that particular merchantId or not
        // we can also do sql operation as well with where conditions
        ApiKey apiKey = apiKeyRepository.findById(ApiKeyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFound("ApiKey",ApiKeyId));
        apiKey.setEnabled(false);
        apiKeyRepository.save(apiKey);


    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotateKey(UUID merchantId, UUID keyId) {

        //we need to find if the keyId with the same merchant is available or not
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFound("ApiKey",keyId));

        //🤯 if key is disabled we should not retate
        if(!apiKey.isEnabled())
            throw new RuntimeException("Cannot rotate a disabled key");

        //create a new password or secret , set the current in previous and new one in present

        String newRawSecret = RandomizerUtil.randomBase64(40);//TODO : replace with cryptographic random hex
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiredAt(LocalDateTime.now().plusHours(24));

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(
                apiKey.getId(),
                apiKey.getKeyId(),
                newRawSecret,
                apiKey.getEnvironment()
        );

    }


}
