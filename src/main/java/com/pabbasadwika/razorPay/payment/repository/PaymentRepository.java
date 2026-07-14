package com.pabbasadwika.razorPay.payment.repository;

import com.pabbasadwika.razorPay.payment.dto.response.OrderResponse;
import com.pabbasadwika.razorPay.payment.entity.OrderRecord;
import com.pabbasadwika.razorPay.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByOrder_id(OrderRecord orderID);
}
