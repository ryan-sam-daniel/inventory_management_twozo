package com.twozo.storage;

import com.twozo.bill.Billing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillStorage {
    private static final Map<Integer, List<Billing>> allBills = new HashMap<>();

    public void addToBillStorage(Billing bill) {
        if (!allBills.containsKey(bill.getBillId())) {
            allBills.put(bill.getBillId(), new ArrayList<>());
        }
        allBills.get(bill.getBillId()).add(bill);
    
    }

    public List<Billing> getBill(int id) {
        return allBills.getOrDefault(id, new ArrayList<>());
    }

    // Method to return all bills (Fix for WeeklySalesReportGenerator)
    public List<Billing> getAllBills() {
        List<Billing> allBillsList = new ArrayList<>();
        for (List<Billing> bills : allBills.values()) {
            allBillsList.addAll(bills);
        }
        return allBillsList; // Return all stored bills as a flat list
    }

}
