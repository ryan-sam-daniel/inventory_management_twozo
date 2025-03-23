package com.twozo.product;

public class ProductPurchaseCost implements ProductCostCalculationService {
    public double calculate(final Product product){
        return (product.getPurchasePrice() * product.getStockQuantity());
    }
}
