package com.twozo.inventory;

import java.util.ArrayList;
import com.twozo.product.*;

public class InventoryItems {
    private static ArrayList <Product> inventoryItems;
    
    //this constructor creates a list that can have Product objects in it
    public InventoryItems(){
        inventoryItems = new ArrayList<>();
    }

    //this method adds product objects to the inventory list
    public void addProduct(Product product){
        inventoryItems.add(product);
    }

    public ArrayList<Product> getInventoryItemList(){
        return inventoryItems;
    }
}
