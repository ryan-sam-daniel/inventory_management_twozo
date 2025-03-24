package com.twozo.bill;

import java.util.List;

import com.twozo.product.Product;

public class BillPrinter {
    public void showBill(final List<Product> billItems) {
        billItems.forEach(product -> System.out.println(
                "Name: " + product.getName() +
                " | Quantity: " + product.getStockQuantity() 
        ));
    }
}    

