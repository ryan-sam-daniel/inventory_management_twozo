package com.twozo.bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twozo.inventory.StockManagementService;
import com.twozo.product.Product;

public class Bill implements Billing{
    private final int billId;
    private final List<Product> billItems;
    private final LocalDateTime billDateTime;
    private final StockManagementService stockManager;
    private double discountedAmount;
    private double totalAmount;

    public Bill(final int id, final StockManagementService stockManager){
        this.billId = id;
        this.stockManager = stockManager;
        this.billItems = new ArrayList<>();
        this.billDateTime = LocalDateTime.now(); // Store bill timestamp
        this.discountedAmount = 0;
        this.totalAmount = 0;
    }

    @Override
    public void addProductToBill(final Product product, final int quantity) {
        if (product.getStockQuantity() >= quantity) { // Check stock availability
            boolean productExists = false;
    
            for (Product item : billItems) {
                if (item.getName().equalsIgnoreCase(product.getName())) {
                    item.setStockQuantity(item.getStockQuantity() + quantity);
                    productExists = true;
                    break;
                }
            }
    
            if (!productExists) {
                billItems.add(new Product(
                        product.getName(), product.getPurchasePrice(), product.getMrp(),
                        product.getTaxPercentage(), product.getSellingPrice(),
                        product.getDiscountPercentage(), quantity));
            }
    
            // Use StockManager to update stock
            stockManager.removeStock(product, quantity);
        } else {
            System.out.println("Not enough stock");
        }
    }

    @Override
    public void removeProductFromBill(final Product product, final int quantity){
        for (Product item : billItems) {
            if (item.getName().equalsIgnoreCase(product.getName())) {
                if (item.getStockQuantity() >= quantity) {
                    item.setStockQuantity(item.getStockQuantity() - quantity);
                    
                    // If quantity becomes zero, remove it from bill
                    if (item.getStockQuantity() == 0) {
                        billItems.remove(item);
                    }
    
                    // Add back stock to inventory
                    stockManager.addStock(product, quantity);
                    System.out.println("Product removed from bill.");
                } else {
                    System.out.println("Not enough quantity in bill to remove.");
                }
                return;
            }
        }
    }
    
    @Override
    public List<Product> getBillItems() {
        return Collections.unmodifiableList(billItems);
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
    public void setDiscountedAmount(final double amount){
        this.discountedAmount += amount;
    }

    @Override
    public double getTotalAmount(){
        return totalAmount;
    }

    @Override
    public void setTotalAmount(final double amount){
        this.totalAmount += amount;
    }
}

