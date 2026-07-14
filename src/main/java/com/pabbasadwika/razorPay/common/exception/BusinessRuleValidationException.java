package com.pabbasadwika.razorPay.common.exception;

import lombok.Getter;

@Getter
public class BusinessRuleValidationException extends RuntimeException{
    private final String errorCode;

    public BusinessRuleValidationException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
