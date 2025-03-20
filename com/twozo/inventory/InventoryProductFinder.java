package com.twozo.inventory;

import com.twozo.product.Product;

public class InventoryProductFinder implements ProductFinderService{
    InventoryItems inventoryItems;  
          
    public InventoryProductFinder(InventoryItems inventoryItems){
        this.inventoryItems = inventoryItems;
    }

    @Override
    public Product getProduct(String name){
        for (Product product : inventoryItems.getInventoryItemList()){
            if (product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }

}
