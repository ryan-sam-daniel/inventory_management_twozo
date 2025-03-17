package com.twozo.bill;

import java.time.LocalDateTime;
import java.util.List;

import com.twozo.product.Product;

public interface Billing {
    void addProductToBill(Product product, int quantity);
    int getBillId();  
    List<Product> getBillItems();  
    LocalDateTime getBillDateTime(); 
}
