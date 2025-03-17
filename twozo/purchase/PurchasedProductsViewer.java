package com.twozo.purchase;

import com.twozo.storage.*;
import com.twozo.product.*;

public class PurchasedProductsViewer {
    private final PurchaseDetailsStorer purchaseDetailsStorer;

    public PurchasedProductsViewer(PurchaseDetailsStorer purchaseDetailsStorer) {
        this.purchaseDetailsStorer = purchaseDetailsStorer;
    }

    public final void viewPurchases() {
        if ( purchaseDetailsStorer.getPurchaseDetails().isEmpty() ) {
            System.out.println("No purchases found.");
            return;
        }

        for ( PurchaseEntry purchaseEntry : purchaseDetailsStorer.getPurchaseDetails() ) {
            Product product = purchaseEntry.getProduct();
            System.out.println("Name: " + (product).getName() + " | Quantity: " + product.getStockQuantity());
        }
    }
}
