package Inventory_management.inventory;

import java.util.ArrayList;

import Inventory_management.product.Product;


public class InventoryStockChecker {
    //This shows stocks that has less quantity than a threshold value of 5
    public void lessStockReport(ArrayList<Product> inventory_list){
        
        for (Product p : inventory_list){
            if(p.getStockQuantity() < 5){
                System.out.println("Name : "+p.getName()+ "has less stocks at minimum level");
            }
        }
    }
}
