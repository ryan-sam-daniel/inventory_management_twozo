package Inventory_management.bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Inventory_management.product.Product;

public class BillStorage {
    //This method adds the bill along with bill_id to a hashmap for future references
    private static HashMap<Integer,ArrayList<Product>> all_bills = new HashMap<>();

    // public BillStorage(int id) {
    //         super(id);
    //     }
    

    //Add all product list along wiht id to billing data
    public void addToBillingData(int bill_id,ArrayList<Product> bill_items){
       if (!bill_items.isEmpty()){
            all_bills.put(bill_id,new ArrayList<>(bill_items));
       }
    }

    //getter to get all the bills
    public ArrayList<Product> getAllBills(int id){
        for (Map.Entry<Integer,ArrayList<Product>> entry : all_bills.entrySet()  ){
            if (id == entry.getKey()){
                return entry.getValue();
            }
        }
        return null;
    }
}
