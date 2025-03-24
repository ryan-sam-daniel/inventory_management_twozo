package com.twozo.payment;

import java.util.Scanner;

public class PaymentMethodHandler {
    Scanner scanner = new Scanner(System.in);
    CardPayment cardPayment = new CardPayment();
    CashPayment cashPayment = new CashPayment();
    UpiPayment upiPayment = new UpiPayment();
    PaymentRequest paymentRequest; 

    public PaymentMethodHandler(PaymentRequest paymentRequest ){
        this.paymentRequest = paymentRequest;
    }

    public void chooseMethod(){
        System.out.println("Payment: \n1)Cash \n2)UPI \n3)Card");
        final int paymnetChoice = scanner.nextInt();
        switch (paymnetChoice ){
            case 1:
                cashPayment.pay(paymentRequest);
                break;
            case 2:
                upiPayment.pay(paymentRequest);
                break;
            case 3:
                cardPayment.pay(paymentRequest);
                break;
        }
    }
}
