package Inventory_management.bill;

import Inventory_management.product.Product;
import Inventory_management.product.ProductTallying;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bill implements BillOperation {
    private ArrayList<Product> bill_items;
    private static HashMap<Integer,ArrayList<Product>> all_bills = new HashMap<>();
    private int bill_id;

    //Construct for Bill 
    public Bill(int id){
        this.bill_id=id;
        this.bill_items= new ArrayList<Product>();
    }

    //This method adds products to the bill
    @Override
    public void addToBill(Product product, int quantity){
        if (product.getStockQuantity() > quantity){
            bill_items.add(new Product(product.getName(), product.getPurchasePrice(), product.getMrp(), product.getTaxPercentage(), product.getSellingPrice(), product.getDiscountPercentage(), quantity));
            ProductTallying productTallying = new ProductTallying();
            productTallying.decrease_quantity(product,quantity);
        }
        else{
            System.out.println("not enough stock");
        }
        
    }

    //This method adds the bill along with bill_id to a hashmap for future references
    public void addToBillingData(){
       if (!bill_items.isEmpty()){
            all_bills.put(bill_id,new ArrayList<>(bill_items));
       }
    }

    public ArrayList<Product> getBillItems() {
        return bill_items;
    }

    public static ArrayList<Product> getAllBills(int id){
        for (Map.Entry<Integer,ArrayList<Product>> entry : all_bills.entrySet()  ){
            if (id == entry.getKey()){
                return entry.getValue();
            }
        }
        return null;
    }
    
}


