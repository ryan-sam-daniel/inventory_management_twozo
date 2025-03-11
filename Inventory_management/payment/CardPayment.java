package Inventory_management.payment;

import Inventory_management.store.StorePaymentOperation;

public class CardPayment implements StorePaymentOperation{
    @Override
    public void print(){
        System.out.println("Thanks for puchasing via card");
    }
    
}
