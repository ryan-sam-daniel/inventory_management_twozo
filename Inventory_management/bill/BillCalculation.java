package Inventory_management.bill;

import java.util.ArrayList;
import Inventory_management.product.*;      


public class BillCalculation {
    //This method return the total amount of bill without discount
    public double totalbill(ArrayList<Product> bill_items){
        double total_amount = 0;
        for (Product p : bill_items){
            total_amount += (p.getSellingPrice()*p.getStockQuantity());
        }
        return total_amount;
    }

    //This method returns total amount after the discount
    public double discountedbill(double total_amount, double discountPercentage){
        double discount_amount = ((total_amount) - ((total_amount*discountPercentage)/100));
        return discount_amount;
    }
}
