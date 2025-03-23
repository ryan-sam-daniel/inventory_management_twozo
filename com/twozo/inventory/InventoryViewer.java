package com.twozo.inventory;

import com.twozo.product.Product;

public class InventoryViewer implements InventoryViewerService{
    private final InventoryStorage inventoryItems;
    
    public InventoryViewer(final InventoryStorage inventoryItems){
        this.inventoryItems = inventoryItems;
    }
    
    @Override
    public void viewInventory() {
        for (final Product product : inventoryItems.getInventoryItemList()) {
            System.out.println(product.toString());
        }
    }
}

