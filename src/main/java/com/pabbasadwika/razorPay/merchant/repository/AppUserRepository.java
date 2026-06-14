package com.pabbasadwika.razorPay.merchant.repository;

import com.pabbasadwika.razorPay.merchant.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
