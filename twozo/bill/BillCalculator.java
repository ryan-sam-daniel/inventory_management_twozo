package com.twozo.bill;

import com.twozo.product.Product;

public class BillCalculator {
    
    public double totalBill(Billing bill) {
        double totalAmount =0;
        for (Product product : bill.getBillItems()){
            totalAmount += product.getSellingPrice() * product.getStockQuantity();
        }
        return totalAmount;
    }

    public double discountedBill(double totalAmount, double discountPercentage) {
        return totalAmount - (totalAmount * discountPercentage / 100);
    }
}
