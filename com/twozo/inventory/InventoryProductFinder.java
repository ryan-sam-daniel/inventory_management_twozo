package com.twozo.inventory;

import com.twozo.product.Product;

public class InventoryProductFinder implements ProductFinderService{
    private final InventoryStorage inventoryItems;  
          
    public InventoryProductFinder(final InventoryStorage inventoryItems){
        this.inventoryItems = inventoryItems;
    }

    @Override
    public Product findProductByName(final String name){
        for (final Product product : inventoryItems.getInventoryItemList()){
            if (product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }

}
