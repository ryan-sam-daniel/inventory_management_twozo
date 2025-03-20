package com.twozo.bill;

import java.time.LocalDateTime;
import java.util.List;

import com.twozo.product.Product;

public interface BillingService {

    /**
     * @param product Product object is given as to get product name, product selling price
     * @param quantity Quantity specified by the customer
     */
    void addProductToBill(Product product, int quantity);

    /**
     * @return billId It the bill id( phone number ) of the customer
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
     * @param amount calculated discounted amount is given as input
     */
    void setDiscountedAmount(double amount);
    
    /**
     * @return totalAmount it returns the total amount of customer's bill without discounted amount
     */
    double getTotalAmount();

    /**
     * @param amount calculated total amount is given as input
     */
    void setTotalAmount(double amount);
}
