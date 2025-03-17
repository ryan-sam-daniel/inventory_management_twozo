package com.twozo.reports;

import com.twozo.inventory.InventoryItems;
import com.twozo.product.Product;
import java.util.List;

public class StockReportGenerator implements ReportGenerator{
    private final InventoryItems inventory;

    public StockReportGenerator(InventoryItems inventory) {
        this.inventory = inventory;
    }

    @Override
    public void generateReport() {
        final List<Product> products = inventory.getInventoryItemList();

        if (products.isEmpty()) {
            System.out.println("\nNo products in stock.");
            return;
        }

        System.out.println("\n**Stock Summary Report**:");
        for (Product product : products) {
            System.out.println(product.getName() + " | Stock: " + product.getStockQuantity() +
                    " | Price: " + product.getSellingPrice());
        }

        for (Product product : products){
            if (product.getStockQuantity() <= 5){
                System.out.println("\n"+product.getName()+" needs to be restocked ");
            }
        }
    }
}
