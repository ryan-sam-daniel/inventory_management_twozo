package Inventory_management.bill;

import Inventory_management.product.Product;
import Inventory_management.product.ProductTallying;

import java.util.ArrayList;



public class Bill{
    private ArrayList<Product> bill_items;

    private int bill_id;

    //Construct for Bill 
    public Bill(int id){
        this.bill_id=id;
        this.bill_items= new ArrayList<Product>();
    }

    //This method adds products to the bill
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

    //getter to get Products in the bill
    public ArrayList<Product> getBillItems() {
        return bill_items;
    }

    //get
    public int getBillId(){
        return bill_id;
    }

    
    
}


