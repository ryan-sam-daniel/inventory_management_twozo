package Inventory_management.store;

import Inventory_management.bill.Bill;

public interface StoreBillOperation {
    public void addProduct(Bill bill);
    public void viewProduct(Bill bill);
    public void payment(Bill bill,int bill_id);
}
