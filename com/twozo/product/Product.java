package com.twozo.product;

public class Product {
    private final String name;
    private final double purchasePrice;
    private final double mrp;
    private final int taxPercentage;
    private final double sellingPrice;
    private final double discountPercentage;
    private int stockQuantity;

    public Product(final String name, final double purchasePrice, final double mrp, final int taxPercentage, 
                final double sellingPrice, final double discountPercentage, final int stockQuantity) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.mrp = mrp;
        this.taxPercentage = taxPercentage;
        this.sellingPrice = sellingPrice;
        this.discountPercentage = discountPercentage;
        this.stockQuantity = stockQuantity;
    }

    //This method is for display the product
    @Override
    public String toString() {
        return "Product: " + name + ", Quantity: " + stockQuantity;
    }

    public String getName(){
        return name;
    }

    public double getSellingPrice(){
        return sellingPrice;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public double getPurchasePrice(){
        return purchasePrice;
    }

    public double getMrp(){
        return mrp;
    }

    public int getTaxPercentage(){
        return taxPercentage;
    }

    public double getDiscountPercentage(){
        return discountPercentage;
    }

    public void setStockQuantity(final int quantity){
        this.stockQuantity=quantity;
    }

}
