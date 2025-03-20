package com.twozo.purchase;

import java.util.Scanner;

public class StockPurchaseHandler {
    private final PurchaseService purchaseProcessor;
    
    public StockPurchaseHandler(PurchaseService purchaseProcessor) {
        this.purchaseProcessor = purchaseProcessor;
    }

    public void purchaseStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Product Name : ");    
        String productName = scanner.nextLine();
        System.out.println("Enter the quantity : ");
        int productQuantity = scanner.nextInt();
        purchaseProcessor.addProduct(productName, productQuantity);  
    }   
}
