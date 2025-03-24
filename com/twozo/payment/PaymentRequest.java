package com.twozo.payment;

public class PaymentRequest {
    private final long id;
    private final double amount;

    public PaymentRequest(long id , double amount){
        this.id = id;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}
