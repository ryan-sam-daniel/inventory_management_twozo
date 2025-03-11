package Inventory_management.inventory;

import java.util.ArrayList;
import Inventory_management.product.*;


public class Inventory {
    private static ArrayList <Product> inventory_list;
    
        //this constructor creates a list that can have Product objects in it
        public Inventory(){
            inventory_list = new ArrayList<>();
        }
    
        //this method adds product objects to the inventory list
        public void addProduct(Product product){
            inventory_list.add(product);
        }
    
        //this method gets each product from list and calls the display method from product class
        public void displayProducts(){
            for (Product p : inventory_list){
                p.display_product();
            }
        }
    
        //this method finds the product in the list and returns the product
        public Product getProduct(String name){
            for (Product p : inventory_list){
                if (p.getName().equalsIgnoreCase(name)){
                    return p;
                }
            }
            return null;
        }
    
        //getter
        public ArrayList<Product> getinventory_list(){
            return inventory_list;
    }


}
