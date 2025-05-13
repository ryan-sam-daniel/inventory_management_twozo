package com.twozo.app.model;

import org.springframework.stereotype.Component;

@Component
public class FinalAmountRequest {
    public double amount;
    public double rate;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public FinalAmountRequest() {
    }
}
