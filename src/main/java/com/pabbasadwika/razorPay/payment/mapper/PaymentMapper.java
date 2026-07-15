package com.pabbasadwika.razorPay.payment.mapper;

import com.pabbasadwika.razorPay.payment.dto.response.PaymentResponse;
import com.pabbasadwika.razorPay.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target = "orderId",source = "order.id")
    PaymentResponse toResponse(Payment payment);

    //we can create multiple mappings here
    @Mapping(target = "orderId",source = "order.id")
    List<PaymentResponse> toResponseList(List<Payment> payments);
}
