package com.pabbasadwika.razorPay.merchant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//when ever something happens on razorpay side I want to know what happens and we want to listen to those events
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;

    //is a url in which razorpay let the merchant know  , will create multiple url according to the stage
    @Column(nullable = false,length = 500)
    private String targetUrl;

    @Column(length = 255)
    private String webhookSecretHash;

    @Column(nullable = false)
    private Boolean enabled = true;

    //what are all the events happens
    @Column(length = 255)
    private String eventTypes;



}
