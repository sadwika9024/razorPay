package com.pabbasadwika.razorPay.merchant.mapper;

import com.pabbasadwika.razorPay.merchant.dto.request.ApiKeyCreateResponse;
import com.pabbasadwika.razorPay.merchant.dto.response.ApiKeyResponse;
import com.pabbasadwika.razorPay.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {


    ApiKeyCreateResponse toApiKeyCreateResponse(ApiKey apiKey);

    List<ApiKeyResponse> toListOfApiKeyResponse(List<ApiKey> apiKeys);
}
