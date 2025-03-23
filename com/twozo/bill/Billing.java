package com.twozo.bill;

import java.time.LocalDateTime;
import java.util.List;

import com.twozo.product.Product;

public interface Billing {

    /**
     * @param product Product object is given as to get product name, product selling price
     * @param quantity Quantity specified by the customer
     */
    void addProductToBill(Product product, int quantity);

    void removeProductFromBill(Product product , int quantity);

    /**
     * @return billId Unique identifier for the bill.
     */
    int getBillId();  

    /**
     * @return List<Product> this list contains the Product inside a bill 
     */
    List<Product> getBillItems();  

    /**
     * @return LocalDateTime this specifies the time of the bill
     */
    LocalDateTime getBillDateTime(); 

    /**
     * @return DiscountedAmount it returns the customer bill amount after discount
     */
    double getDiscountedAmount();

    /**
     * @param amount The calculated discounted amount to set.
     */
    void setDiscountedAmount(double amount);
    
    /**
     * @return totalAmount it returns the total amount of customer's bill without discounted amount
     */
    double getTotalAmount();

    /**
     * @param amount The calculated total amount to set.
     */
    void setTotalAmount(double amount);
}
