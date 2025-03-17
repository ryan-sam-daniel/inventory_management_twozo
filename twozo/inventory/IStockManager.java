package com.twozo.inventory;

import com.twozo.product.Product;

public interface IStockManager {
    void addStock(Product product, int quantity);
    boolean removeStock(Product product, int quantity);
}

