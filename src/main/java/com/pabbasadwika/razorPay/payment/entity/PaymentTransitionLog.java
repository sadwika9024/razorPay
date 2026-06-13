package com.pabbasadwika.razorPay.payment.entity;

import com.pabbasadwika.razorPay.common.enums.PaymentActor;
import com.pabbasadwika.razorPay.common.enums.PaymentEvent;
import com.pabbasadwika.razorPay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
//
@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status",length = 30)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status",length = 30)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event",nullable = false,length = 30)
    private PaymentEvent eventStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "actor",length = 100)
    private PaymentActor actor;

    private String reason;

    private LocalDateTime occuredAt;

}

