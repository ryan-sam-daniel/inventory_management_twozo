package com.twozo.purchase;

import com.twozo.product.Product;
import com.twozo.product.ProductCostCalculationService;
import com.twozo.storage.PurchaseDetailsStorer;

public class PurchasedProductsViewer {
    private final PurchaseDetailsStorer purchaseDetailsStorer;
    private final  ProductCostCalculationService purchaseCostcalculator;

    public PurchasedProductsViewer(final PurchaseDetailsStorer purchaseDetailsStorer, final ProductCostCalculationService purchaseCostcalculator) {
        this.purchaseDetailsStorer = purchaseDetailsStorer;
        this.purchaseCostcalculator = purchaseCostcalculator;
    }

    public final void viewPurchases() {
        if ( purchaseDetailsStorer.getPurchaseDetails().isEmpty() ) {
            System.out.println("No purchases found.");
            return;
        }

        for ( final Purchase purchase : purchaseDetailsStorer.getPurchaseDetails() ) {
            final Product product = purchase.getProduct();
            final double purchaseCost = purchaseCostcalculator.calculate(product);
            System.out.println("Name: " + (product).getName() + " | Quantity: " + product.getStockQuantity() + " | Cost: " + purchaseCost);
        }
    }
}
