package com.twozo.app.validator;

import org.springframework.stereotype.Component;

@Component
public class QuantityValidator {
    public boolean validateStockQuantity(final int quantity){
        return quantity > 0;
    }

    public boolean validateRate(final double rate){
        return  (rate > 0 && rate <= 100);
    }

    public boolean validateAmount(final double amount){
        return amount>=0;
    }

    public boolean validatePhoneNo (final String phoneNo){
        return (phoneNo.length()==10);
    }
}
