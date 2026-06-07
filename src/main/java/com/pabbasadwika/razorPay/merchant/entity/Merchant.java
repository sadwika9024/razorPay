package com.pabbasadwika.razorPay.merchant.entity;

import com.pabbasadwika.razorPay.common.enums.BusinessType;
import com.pabbasadwika.razorPay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 200)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(length = 10)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BusinessType businessType;

    @Column(length = 100)
    private String businessName;

    @Column(length = 200)
    private String websiteUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 200,nullable = false)
    private MerchantStatus status;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfscCode;

    @Column(length = 200)
    private String settlementBankAccountHolderName;

}
