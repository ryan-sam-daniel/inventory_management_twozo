package com.twozo.bill;

import com.twozo.product.Product;

public class BillCalculator {
    
    public double totalBillAmount(final Billing bill) {
        double totalAmount =0;
        
        for (final Product product : bill.getBillItems()){
            totalAmount += product.getSellingPrice() * product.getStockQuantity();
        }
        return totalAmount;
    }

    public double discountedBillAmount(final double totalAmount, final double discountPercentage) {
        return totalAmount - (totalAmount * discountPercentage / 100);
    }
}
