package com.twozo.inventory;

import com.twozo.product.*;

public class InventoryViewer {
    InventoryItems inventoryItems;
    
    public InventoryViewer(InventoryItems inventoryItems){
        this.inventoryItems = inventoryItems;
    }
    
    public void displayProducts() {
        for (Product product : inventoryItems.getInventoryItemList()) {
            product.display_product();
        }
    }
}

