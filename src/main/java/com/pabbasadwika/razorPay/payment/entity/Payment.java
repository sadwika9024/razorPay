package com.pabbasadwika.razorPay.payment.entity;

import com.pabbasadwika.razorPay.common.entity.Money;
import com.pabbasadwika.razorPay.common.enums.OrderStatus;
import com.pabbasadwika.razorPay.common.enums.PaymentMethod;
import com.pabbasadwika.razorPay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id" , nullable = false)
    private OrderRecord order;

    @Column(nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(nullable = false,length = 100)
    private String idempontencyKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 30)
    private PaymentStatus paymentStatus;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "method_details",columnDefinition = "jsonb")
    private Map<String, Object> paymentMethodDetails;

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String failureReason;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 255)
    private String errorDescription;

    private LocalDateTime authorizedAt;

    private LocalDateTime capturedAt;

    private LocalDateTime failedAt;

    private LocalDateTime refundedAt;

    private LocalDateTime settledAt;




}
