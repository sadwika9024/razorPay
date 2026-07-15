package com.pabbasadwika.razorPay.merchant.service.impl;

import com.pabbasadwika.razorPay.common.enums.MerchantStatus;
import com.pabbasadwika.razorPay.common.enums.UserRole;
import com.pabbasadwika.razorPay.common.exception.DuplicateResourceException;
import com.pabbasadwika.razorPay.merchant.dto.request.MerchantSignupRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.MerchantResponse;
import com.pabbasadwika.razorPay.merchant.entity.AppUser;
import com.pabbasadwika.razorPay.merchant.entity.Merchant;
import com.pabbasadwika.razorPay.merchant.mapper.MerchantMapper;
import com.pabbasadwika.razorPay.merchant.repository.AppUserRepository;
import com.pabbasadwika.razorPay.merchant.repository.MerchantRepository;
import com.pabbasadwika.razorPay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;


    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","emailID already exist : "+request.email());
        }
        Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);

        merchant = merchantRepository.save(merchant);

        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) // TODO: encrypt using bycrpt
                .role(UserRole.OWNER)
                            .build();

        appUserRepository.save(appUser);

        return merchantMapper.toMerchantResponse(merchant);
    }
}
