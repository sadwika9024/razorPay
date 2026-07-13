package com.pabbasadwika.razorPay.merchant.controller;

import com.pabbasadwika.razorPay.merchant.dto.request.ApiKeyCreateResponse;
import com.pabbasadwika.razorPay.merchant.dto.request.CreateApiKeyRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.ApiKeyResponse;
import com.pabbasadwika.razorPay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                       @Valid @RequestBody CreateApiKeyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.create(merchantId, request));
    }
    //to get all the apikey with that particular merchants
    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> list(@PathVariable UUID merchantId){

        return ResponseEntity.ok(apiKeyService.list(merchantId));
    }

    @DeleteMapping("/{keyId}")
    public ResponseEntity<Void> revoke(@PathVariable UUID merchantId,@PathVariable UUID keyId ){
        apiKeyService.revoke(merchantId,keyId);
        return ResponseEntity.noContent().build();
    }

    //so we will store apikeyId, secret1, secret2 , grace period of secret 1
    //grace period until which that secret1 is valid
    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse> rotateKey(@PathVariable UUID merchantId,@PathVariable UUID keyId){
        return ResponseEntity.ok(apiKeyService.rotateKey(merchantId,keyId));
    }
}
