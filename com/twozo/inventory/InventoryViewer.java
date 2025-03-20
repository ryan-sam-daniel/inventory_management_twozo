package com.twozo.inventory;

import com.twozo.product.Product;

public class InventoryViewer implements InventoryViewerService{
    private InventoryItems inventoryItems;
    
    public InventoryViewer(InventoryItems inventoryItems){
        this.inventoryItems = inventoryItems;
    }
    
    @Override
    public void viewInventory() {
        for (Product product : inventoryItems.getInventoryItemList()) {
            System.out.println(product.toString());
        }
    }
}

