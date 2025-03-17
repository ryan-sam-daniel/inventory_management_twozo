package com.twozo.payment;

public class UpiPayment implements PaymentService {
    @Override
    public void print(){
        System.out.println("Payment via Upi is successfull");
    }
}

