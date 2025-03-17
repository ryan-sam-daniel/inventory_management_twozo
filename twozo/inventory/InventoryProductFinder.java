package com.twozo.inventory;

import com.twozo.product.*;

public class InventoryProductFinder implements IProductFinder{
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
