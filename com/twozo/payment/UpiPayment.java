package com.twozo.payment;

public class UpiPayment implements PaymentService {
    @Override
    public void pay(){
        System.out.println("Payment via Upi is successfull");
    }
}

