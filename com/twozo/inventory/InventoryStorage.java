package com.twozo.inventory;

import java.util.List;

import com.twozo.product.Product;

public interface InventoryStorage {
    void addProductToInventory(Product product);
    List<Product> getInventoryItemList();
}