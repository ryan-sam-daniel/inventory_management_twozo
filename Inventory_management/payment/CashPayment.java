package Inventory_management.payment;

import Inventory_management.store.StorePaymentOperation;

public class CashPayment implements StorePaymentOperation{
    @Override
    public void print(){
        System.out.println("Thanks for purchasing via Cash");
    }
}
