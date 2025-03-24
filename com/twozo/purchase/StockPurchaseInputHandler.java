package com.twozo.purchase;

import java.util.Scanner;

public class StockPurchaseInputHandler {
    private final PurchaseService purchaseProcess;
    
    public StockPurchaseInputHandler(final PurchaseService purchaseProcess) {
        this.purchaseProcess = purchaseProcess;
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
        purchaseProcess.addProduct(productName, productQuantity);  
    }
}
