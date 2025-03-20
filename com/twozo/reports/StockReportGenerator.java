package com.twozo.reports;

import java.util.ArrayList;
import java.util.List;

import com.twozo.inventory.InventoryItems;
import com.twozo.product.Product;

public class StockReportGenerator implements ReportGenerator{
    private final InventoryItems inventory;

    public StockReportGenerator(InventoryItems inventory) {
        this.inventory = inventory;
    }

    @Override
    public Report generateReport() {
        final List<Product> products = inventory.getInventoryItemList();
        List<String> reportContent =new ArrayList<>();

        if (products.isEmpty()) {
            reportContent.add("\nNo products in stock.");
            return null;
        }

        for (Product product : products) {
            reportContent.add(product.getName() + " | Stock: " + product.getStockQuantity() +
                    " | Price: " + product.getSellingPrice());
            if (product.getStockQuantity() <= 5){
                reportContent.add("\n*********");
                reportContent.add("\n"+product.getName()+" needs to be restocked ");
                reportContent.add("\n*********");
            }
        }
        return new Report("Stock Report", reportContent);
    }
}
