package com.pabbasadwika.razorPay.common.enums;

public enum PaymentStatus {
    CREATED,
    AUTHORIZING,//mean its currently being processed same goes to captured its like retry mechanism
    AUTHORIZED,
    CAPTURED,
    CAPTURING,
    CANCELLED,
    FAILED,
    REFUNDED,
    PARTIALLY_REFUNDED,
    SETTLED,
    AUTH_EXPIRED
}
