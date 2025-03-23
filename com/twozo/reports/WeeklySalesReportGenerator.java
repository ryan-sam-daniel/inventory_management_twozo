package com.twozo.reports;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twozo.bill.Billing;
import com.twozo.product.Product;
import com.twozo.purchase.Purchase;
import com.twozo.storage.BillStorage;
import com.twozo.storage.PurchaseDetailsStorer;

public class WeeklySalesReportGenerator implements ReportGenerator{
    private final BillStorage billStorage;
    private final PurchaseDetailsStorer purchaseDetailsStorer;

    public WeeklySalesReportGenerator(final BillStorage billStorage, final PurchaseDetailsStorer purchaseDetailsStorer) {
        this.billStorage = billStorage;
        this.purchaseDetailsStorer = purchaseDetailsStorer;
    }

    @Override
    public Report generateReport() {
        final List<Billing> allBills = billStorage.getAllBills();
        final List<Purchase> purchaseEntries = purchaseDetailsStorer.getPurchaseDetails();
        final LocalDateTime oneWeekAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        final List<String> reportContents = new ArrayList<>();
        double totalWeeklySales = 0;
        double totalWeeklyPurchases=0;
        final Map<String, Integer> productSalesCount = new HashMap<>();
        String mostSoldProduct = null;
        int maxSales = Integer.MIN_VALUE;
        String lessSoldProduct = null;
        int minSales = Integer.MAX_VALUE;

        for (final Billing bill : allBills) {
            if (bill.getBillDateTime().isAfter(oneWeekAgo)) {
                totalWeeklySales += bill.getDiscountedAmount();
                for (final Product product : bill.getBillItems()) {
                    productSalesCount.put(product.getName(),
                        productSalesCount.getOrDefault(product.getName(), 0) + product.getStockQuantity());
                }
            }
        }

        for (final Purchase purchases : purchaseEntries) {
            if (purchases.getPurchaseDateTime().isAfter(oneWeekAgo)) {
                totalWeeklyPurchases += (purchases.getProduct().getPurchasePrice() * purchases.getProduct().getStockQuantity());

            }
        }

        reportContents.add("----------------------");
        reportContents.add("** Total Weekly Sales: " + totalWeeklySales + " **");
        reportContents.add("** Total Weekly Expenses: " + totalWeeklyPurchases + " **");
        
        if (totalWeeklyPurchases > totalWeeklySales){
            reportContents.add("** Loss Amount : "+(totalWeeklyPurchases-totalWeeklySales)+" **");
        }
        else {
            reportContents.add("** Profit Amount : "+(totalWeeklySales-totalWeeklyPurchases)+" **");
        }

        for (final Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                mostSoldProduct = entry.getKey();
            }
        }

        for (final Map.Entry<String, Integer> entry : productSalesCount.entrySet()) {
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
