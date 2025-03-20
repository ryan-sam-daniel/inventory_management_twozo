package com.twozo.bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.twozo.inventory.StockManagementService;
import com.twozo.product.Product;

public class Bill implements BillingService {
    private final int billId;
    private final List<Product> billItems;
    private final LocalDateTime billDateTime;
    private final StockManagementService stockManager;
    private double discountedAmount;
    private double totalAmount;

    public Bill(int id, StockManagementService stockManager){
        this.billId = id;
        this.stockManager = stockManager;
        this.billItems = new ArrayList<>();
        this.billDateTime = LocalDateTime.now(); // Store bill timestamp
        this.discountedAmount = 0;
        this.totalAmount = 0;
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

    @Override
    public double getDiscountedAmount(){
        return discountedAmount;
    }

    @Override
    public void setDiscountedAmount(double amount){
        discountedAmount += amount;
    }

    @Override
    public double getTotalAmount(){
        return totalAmount;
    }

    @Override
    public void setTotalAmount(double amount){
        totalAmount += amount;
    }
}

