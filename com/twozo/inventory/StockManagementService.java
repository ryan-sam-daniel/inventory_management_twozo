package com.twozo.inventory;

import com.twozo.product.Product;

public interface StockManagementService {
    void addStock(Product product, int quantity);
    boolean removeStock(Product product, int quantity);
}

