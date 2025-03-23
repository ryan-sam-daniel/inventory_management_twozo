package com.twozo.purchase;

import java.util.Scanner;

public class StockPurchaseHandler {
    private final PurchaseService purchaseProcessor;
    
    public StockPurchaseHandler(final PurchaseService purchaseProcessor) {
        this.purchaseProcessor = purchaseProcessor;
    }

    public void purchaseStock() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the Product Name : ");    
        final String productName = scanner.nextLine();
        int productQuantity; 
        do{
            System.out.println("Enter the quantity : ");
            productQuantity = scanner.nextInt();
            if (productQuantity < 1){
                System.out.println("Quantity should not be negative");
            }
        }while(productQuantity<1);
        purchaseProcessor.addProduct(productName, productQuantity);  
    }
}
