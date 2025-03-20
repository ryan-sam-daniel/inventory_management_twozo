package com.twozo.purchase;

import com.twozo.product.Product;
import com.twozo.product.ProductCostCalculationService;
import com.twozo.storage.PurchaseDetailsStorer;

public class PurchasedProductsViewer {
    private final PurchaseDetailsStorer purchaseDetailsStorer;
    private final  ProductCostCalculationService purchasecostcalculator;

    public PurchasedProductsViewer(PurchaseDetailsStorer purchaseDetailsStorer, ProductCostCalculationService purchasecostcalculator) {
        this.purchaseDetailsStorer = purchaseDetailsStorer;
        this.purchasecostcalculator = purchasecostcalculator;
    }

    public final void viewPurchases() {
        if ( purchaseDetailsStorer.getPurchaseDetails().isEmpty() ) {
            System.out.println("No purchases found.");
            return;
        }

        for ( Purchase purchase : purchaseDetailsStorer.getPurchaseDetails() ) {
            Product product = purchase.getProduct();
            double purchaseCost = purchasecostcalculator.calculate(product);
            System.out.println("Name: " + (product).getName() + " | Quantity: " + product.getStockQuantity() + " | Cost: " + purchaseCost);
        }
    }
}
