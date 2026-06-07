package com.pabbasadwika.razorPay.common.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

//embeddable -- a class that can be used in different tables
@Embeddable
public class Money {
    private Integer amountUnits; // Amount in smallest currency unit (e.g., cents)
    private String currency; // ISO 4217 currency code (e.g., "USD", "INR")

    private static Money of(int amountUnits, String currency){
        return new Money(amountUnits, currency);
    }

    public Money(Integer amountUnits, String currency) {
        this.amountUnits = amountUnits;
        this.currency = currency;
    }

    private static Money inr(Integer amountUnits){
        return new Money(amountUnits, "INR");
    }

    public Money add(Money other){
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        }
        return new Money(this.amountUnits+other.amountUnits, this.currency);
    }
    public Money subtract(Money other){
        if(!this.currency.equals(other.currency)){
            throw new IllegalArgumentException("Cannot subtract Money with different currencies");
        }
        return new Money(this.amountUnits-other.amountUnits, this.currency);
    }
}
