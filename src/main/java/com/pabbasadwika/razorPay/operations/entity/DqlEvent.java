package com.pabbasadwika.razorPay.operations.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "dql_event")
public class DqlEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "webhook_events_id",nullable = false)
    private WebhookEvents weebhookEvents;

    @Column(nullable = false)
    private UUID merchnatId;

    @Column(nullable = false)
    private String finalError;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb",nullable = false)
    private Map<String,Object> payload;

    private LocalDateTime movedAt;

    private LocalDateTime replayedAt;

}
