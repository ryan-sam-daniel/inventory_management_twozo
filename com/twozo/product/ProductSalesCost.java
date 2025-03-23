package com.twozo.product;

public class ProductSalesCost implements ProductCostCalculationService{
    public double calculate(final Product product){
        final double revenue = (product.getSellingPrice()*product.getStockQuantity());
        final double tax_amount = (revenue*product.getTaxPercentage())/100;
        return revenue + tax_amount;
    }

}
