package com.pabbasadwika.razorPay.payment.repository;

import com.pabbasadwika.razorPay.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
