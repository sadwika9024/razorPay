package com.pabbasadwika.razorPay.payment.repository;

import com.pabbasadwika.razorPay.payment.entity.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRespository extends JpaRepository<OrderRecord, UUID> {
    boolean existsByMerchantIdAndReceipt(UUID merchantId, String receipt);
}
