package com.twozo.bill;

import com.twozo.product.Product;
import com.twozo.inventory.IStockManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Billing {
    private final int billId;
    private final List<Product> billItems;
    private final LocalDateTime billDateTime;
    private final IStockManager stockManager;

    public Bill(int id, IStockManager stockManager){
        this.billId = id;
        this.stockManager = stockManager;
        this.billItems = new ArrayList<>();
        this.billDateTime = LocalDateTime.now(); // Store bill timestamp
    }

    @Override
    public void addProductToBill(Product product, int quantity) {
        if (product.getStockQuantity() >= quantity) { // Fix stock check
            billItems.add(new Product(
                    product.getName(), product.getPurchasePrice(), product.getMrp(),
                    product.getTaxPercentage(), product.getSellingPrice(),
                    product.getDiscountPercentage(), quantity));
    
            // Use StockManager to update stock
            stockManager.removeStock(product, quantity);
        } else {
            System.out.println("Not enough stock");
        }
    }
    
    @Override
    public List<Product> getBillItems() {
        return billItems;
    }

    @Override
    public int getBillId() {
        return billId;
    }

    @Override
    public LocalDateTime getBillDateTime() {
        return billDateTime;
    }
}
