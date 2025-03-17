package com.twozo.payment;

import java.util.Scanner;

public class PaymentHandler {
    Scanner scanner = new Scanner(System.in);
    CardPayment cardPayment = new CardPayment();
    CashPayment cashPayment = new CashPayment();
    UpiPayment upiPayment = new UpiPayment();

    public void pay(){
        System.out.println("Payment: \n1)Cash \n2)UPI \n3)Card");
        int paymnetChoice = scanner.nextInt();
        switch (paymnetChoice ){
            case 1:
                cashPayment.print();
                break;
            case 2:
                upiPayment.print();
                break;
            case 3:
                cardPayment.print();
                break;
        }
        
    }
}
