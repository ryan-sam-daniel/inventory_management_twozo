package com.twozo.payment;

public class CashPayment implements PaymentService{
    @Override
    public void pay(){
        System.out.println("Payment via Cash is successfull");
    }
}
