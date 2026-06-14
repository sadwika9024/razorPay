package com.pabbasadwika.razorPay.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException{
    private final String resourceName;
    private final Object identifier;


    public ResourceNotFound(String resourceName, Object identifier) {
        super(resourceName + "not found" + identifier);
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
