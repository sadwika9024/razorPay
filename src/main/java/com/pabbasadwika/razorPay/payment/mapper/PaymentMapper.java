package com.pabbasadwika.razorPay.payment.mapper;

import com.pabbasadwika.razorPay.payment.dto.response.PaymentResponse;
import com.pabbasadwika.razorPay.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    PaymentResponse toResponse(Payment payment);
}
