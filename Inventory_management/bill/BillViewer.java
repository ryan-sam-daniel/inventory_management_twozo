package Inventory_management.bill;

import java.util.ArrayList;
import Inventory_management.product.*;

public class BillViewer {
    //this method shows the products in the bill
    public void viewBill(ArrayList<Product> bill_items){
        for (Product product : bill_items){
            System.out.println("Name : "+product.getName()+ " Quantity : "+product.getStockQuantity());
        }
    }
}
