package com.pabbasadwika.razorPay.merchant.mapper;

import com.pabbasadwika.razorPay.merchant.dto.request.MerchantSignupRequest;
import com.pabbasadwika.razorPay.merchant.dto.response.MerchantResponse;
import com.pabbasadwika.razorPay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntityFromSignUpRequest(MerchantSignupRequest merchantSignupRequest);

    MerchantResponse toMerchantResponse(Merchant merchant);

}
