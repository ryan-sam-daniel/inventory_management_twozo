package com.twozo.payment;

public class CardPayment implements PaymentMethods{
    @Override
    public void pay(PaymentRequest paymentRequest){
        System.out.println("Payment via Card for Bill Id "+paymentRequest.getId()+ " of Total Amount "+paymentRequest.getAmount()+ " is successful");
    }
}
