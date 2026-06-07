package com.pabbasadwika.razorPay.payment.entity;

import com.pabbasadwika.razorPay.common.entity.Money;
import com.pabbasadwika.razorPay.common.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_record")
public class OrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //no fk- cross service boundary
    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 50,nullable = false)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @Column(nullable = false)
    private Integer attempts=0;

    //key value pair that a merchant can store,nested json
    @JdbcTypeCode((SqlTypes.JSON))
    @Column(length = 500 , columnDefinition = "jsonb")
    private Map<String, Object> notes;

    @Column(nullable = false)
    private LocalDateTime expireAt;


}
