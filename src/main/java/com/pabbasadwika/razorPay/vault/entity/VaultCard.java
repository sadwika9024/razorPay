package com.pabbasadwika.razorPay.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vault_card")
public class VaultCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 4)
    private String lastFour;

    @Column(nullable = false,length = 6)
    private String bin; // first 6 digits of the card

    @Column(nullable = false)
    //we use hmac algo to encry the pan we get array of bytes
    private byte[] encryptedPan;

    @Column(nullable = false)
    //dek -- string which is used to encrypt the pan , lock under lock
    private byte[] encryptedDek;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private String cardHolderName;

    private LocalDateTime deletedAt;

}
