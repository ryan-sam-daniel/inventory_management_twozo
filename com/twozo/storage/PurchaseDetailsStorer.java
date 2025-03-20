package com.twozo.storage;

import java.util.ArrayList;
import java.util.List;

import com.twozo.purchase.Purchase;

public class PurchaseDetailsStorer {
    private static final List<Purchase> purchase_listitems = new ArrayList<>();

    public void addPurchaseDetails(List<Purchase> purchaseList) {
        purchase_listitems.addAll(purchaseList);
    }

    public List<Purchase> getPurchaseDetails() {
        return purchase_listitems;  
    }
}
