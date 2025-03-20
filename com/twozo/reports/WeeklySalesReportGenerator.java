package com.twozo.reports;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twozo.bill.BillingService;
import com.twozo.product.Product;
import com.twozo.purchase.Purchase;
import com.twozo.storage.BillStorage;
import com.twozo.storage.PurchaseDetailsStorer;

public class WeeklySalesReportGenerator implements ReportGenerator{
    private final BillStorage billStorage;
    private final PurchaseDetailsStorer purchaseDetailsStorer;

    public WeeklySalesReportGenerator(BillStorage billStorage, PurchaseDetailsStorer purchaseDetailsStorer) {
        this.billStorage = billStorage;
        this.purchaseDetailsStorer = purchaseDetailsStorer;
    }

    @Override
    public Report generateReport() {
        final List<BillingService> allBills = billStorage.getAllBills();
        final List<Purchase> purchaseEntries = purchaseDetailsStorer.getPurchaseDetails();
        final LocalDateTime oneWeekAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        final List<String> reportContents = new ArrayList<>();
        double weeklySales = 0;
        double weeklyPurchases=0;
        Map<String, Integer> productSalesCount = new HashMap<>();
        String mostSoldProduct = null;
        int maxSales = Integer.MIN_VALUE;
        String lessSoldProduct = null;
        int minSales = Integer.MAX_VALUE;

        for (BillingService bill : allBills) {
            if (bill.getBillDateTime().isAfter(oneWeekAgo)) {
                weeklySales += bill.getDiscountedAmount();
                for (Product product : bill.getBillItems()) {
                    productSalesCount.put(product.getName(),
                        productSalesCount.getOrDefault(product.getName(), 0) + product.getStockQuantity());
                }
            }
        }

        for (Purchase purchases : purchaseEntries) {
            if (purchases.getPurchaseDateTime().isAfter(oneWeekAgo)) {
                weeklyPurchases += (purchases.getProduct().getPurchasePrice() * purchases.getProduct().getStockQuantity());

            }
        }

        reportContents.add("----------------------");
        reportContents.add("** Total Weekly Sales: " + weeklySales + " **");
        reportContents.add("** Total Weekly Expenses: " + weeklyPurchases + " **");
        
        if (weeklyPurchases > weeklySales){
            reportContents.add("** Loss Amount : "+(weeklyPurchases-weeklySales)+" **");
        }
        else {
            reportContents.add("** Profit Amount : "+(weeklySales-weeklyPurchases)+" **");
        }

        for (Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostSoldProduct = entry.getKey();
            }
        }

        for (Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
            if (entry.getValue() < minSales) {
                minSales = entry.getValue();
                lessSoldProduct = entry.getKey();
            }
        }

        reportContents.add("Most Sold Product: " + mostSoldProduct);
        reportContents.add("Less Sold Product: " + lessSoldProduct);
        return new Report("**Weekly Sales Report (Last 7 Days)**: ", reportContents);
    }
}
