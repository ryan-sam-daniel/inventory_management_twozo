package com.twozo.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twozo.product.Product;

public class InventoryItems {
    private static final List <Product> inventoryItems = new ArrayList<>();
    
    public InventoryItems(){
    }

    //this method adds product objects to the inventory list
    public void addProduct(Product product){
        if (product != null){
            inventoryItems.add(product);
        }
        else{
            System.out.println("Enter a valid product");
        }
        
    }

    public List<Product> getInventoryItemList(){
        return Collections.unmodifiableList(inventoryItems);
    }
}
