package com.twozo.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twozo.bill.BillingService;

public class BillStorage {
    private static final Map<Integer, List<BillingService>> allBills = new HashMap<>();

    public void addToBillStorage(BillingService bill) {
        if (!allBills.containsKey(bill.getBillId())) {
            allBills.put(bill.getBillId(), new ArrayList<>());
        }
        allBills.get(bill.getBillId()).add(bill);
    }

    public List<BillingService> getBill(int id) {
        return allBills.getOrDefault(id, new ArrayList<>());
    }

    // Method to return all bills (Fix for WeeklySalesReportGenerator)
    public List<BillingService> getAllBills() {
        List<BillingService> allBillsList = new ArrayList<>();
        for (List<BillingService> bills : allBills.values()) {
            allBillsList.addAll(bills);
        }
        return allBillsList; // Return all stored bills as a flat list
    }
}
