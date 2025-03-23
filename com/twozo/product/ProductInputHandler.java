package com.twozo.product;

import java.util.Scanner;

public class ProductInputHandler {
    public Product getInputFromUser(Scanner scanner){

        System.out.println("Enter Product Name : ");
        final String name = scanner.nextLine();

        System.out.println("Enter Purchase Price : ");
        final double purchasePrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the tax Percentage : ");
        final int taxPercentage = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the Selling Price : ");
        final double sellingPrice = scanner.nextDouble();
        scanner.nextLine();

        double mrp;
        do {
            System.out.println("Enter the MRP: ");
            mrp = scanner.nextDouble();
            scanner.nextLine();
            if (mrp < sellingPrice) {
                System.out.println("Error: MRP should not be less than selling price. Please enter a valid MRP.");
            }
        } while (mrp < sellingPrice);

        System.out.println("Enter discount percentage : ");
        final double discountPercentage = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the stock quantity : ");
        final int stockQuantity = scanner.nextInt();
        scanner.nextLine();
        
        return new Product(name, purchasePrice, mrp, taxPercentage, sellingPrice, discountPercentage, stockQuantity);
        
    }
}
