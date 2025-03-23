package com.twozo.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twozo.product.Product;

public class InventoryItems implements InventoryStorage {
    private static final List <Product> inventoryList = new ArrayList<>();
    
    //this method adds product objects to the inventory list
    @Override
    public void addProductToInventory(final Product product){
        if (product != null){
            inventoryList.add(product);
        }
        else{
            System.out.println("Enter a valid product");
        }
    }

    @Override
    public List<Product> getInventoryItemList(){
        return Collections.unmodifiableList(inventoryList);
    }
}
