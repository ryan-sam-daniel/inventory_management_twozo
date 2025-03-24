package com.twozo.payment;

public class CashPayment implements PaymentMethods{
    @Override
    public void pay(PaymentRequest paymentRequest){
        System.out.println("Payment via Cash for Bill Id "+paymentRequest.getId()+ " of Total Amount "+paymentRequest.getAmount()+ " is successful");
    }
}
