package com.twozo.purchase;

import com.twozo.inventory.*;
import com.twozo.product.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class StockPurchaseHandler {
    private final PurchaseService purchaseListAdder;
    private final IProductFinder productFinder;
    private final LocalDateTime purchaseDateTime;
    
    public StockPurchaseHandler(PurchaseService purchaseListAdder, IProductFinder iProductFinder) {
        this.purchaseListAdder = purchaseListAdder;
        this.productFinder = iProductFinder;
        this.purchaseDateTime = LocalDateTime.now();
    }

    public void purchaseStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Product Name : ");    
        String productName = scanner.nextLine();
        System.out.println("Enter the quantity : ");
        int productQuantity = scanner.nextInt();
        Product product = productFinder.getProduct(productName);

        if (product!=null) {
            purchaseListAdder.addToList(productName, productQuantity, purchaseDateTime);
        }
        else {
            System.out.println("Product Not found");
        }   
    }   
}
