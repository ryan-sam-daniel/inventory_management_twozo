package com.twozo.payment;

public class CardPayment implements PaymentService{
    @Override
    public void print(){
        System.out.println("Payment via Card is successfull");
    }
}
