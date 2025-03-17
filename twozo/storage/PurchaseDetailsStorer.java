package com.twozo.storage;

import java.util.ArrayList;
import java.util.List;

import com.twozo.purchase.PurchaseEntry;

public class PurchaseDetailsStorer {
    private static final List<PurchaseEntry> purchase_listitems = new ArrayList<>();

    public void addPurchaseDetails(List<PurchaseEntry> purchaseList) {
        purchase_listitems.addAll(purchaseList);
    }

    public List<PurchaseEntry> getPurchaseDetails() {
        return purchase_listitems;  
    }
}
