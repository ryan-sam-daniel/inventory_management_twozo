package com.twozo.inventory;

import com.twozo.product.Product;

public class StockManager implements IStockManager {
    // Increase stock quantity
    public void addStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() + quantity);
    }

    // Decrease stock quantity
    public boolean removeStock(Product product, int quantity) {
        if (product.getStockQuantity() >= quantity) {
            product.setStockQuantity(product.getStockQuantity() - quantity);
            return true;
        } else {
            System.out.println("Not enough stock for " + product.getName());
            return false;
        }
    }
}
 
