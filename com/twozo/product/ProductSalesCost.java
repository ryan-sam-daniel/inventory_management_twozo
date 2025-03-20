package com.twozo.product;

public class ProductSalesCost implements ProductCostCalculationService{
    public double calculate(Product product){
        double revenue = (product.getSellingPrice()*product.getStockQuantity());
        double tax_amount = (revenue*product.getTaxPercentage())/100;
        return revenue + tax_amount;
    }

}
