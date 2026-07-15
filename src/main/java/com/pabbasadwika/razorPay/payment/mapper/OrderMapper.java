package com.pabbasadwika.razorPay.payment.mapper;

import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toOrderResponse(OrderRecord orderRecord);
}
