package com.twozo.reports;

import com.twozo.bill.BillCalculator;
import com.twozo.storage.BillStorage;
import com.twozo.storage.PurchaseDetailsStorer;
import com.twozo.bill.BillViewer;
import com.twozo.bill.Billing;
import com.twozo.product.Product;
import com.twozo.purchase.PurchaseEntry;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklySalesReportGenerator implements ReportGenerator{
    private final BillStorage billStorage;
    private final PurchaseDetailsStorer purchaseDetailsStorer;
    private final BillViewer billViewer;
    private final BillCalculator billCalculator;

    public WeeklySalesReportGenerator(BillStorage billStorage, PurchaseDetailsStorer purchaseDetailsStorer, BillViewer billViewer, BillCalculator billCalculator) {
        this.billStorage = billStorage;
        this.purchaseDetailsStorer = purchaseDetailsStorer;
        this.billViewer=billViewer;
        this.billCalculator=billCalculator;
    }

    @Override
    public void generateReport() {
        final List<Billing> allBills = billStorage.getAllBills();
        final List<PurchaseEntry> purchaseEntries = purchaseDetailsStorer.getPurchaseDetails();
        final LocalDateTime oneWeekAgo = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
        double weeklySales = 0;
        double weeklyPurchases=0;
        Map<String, Integer> productSalesCount = new HashMap<>();
        String mostSoldProduct = null;
        int maxSales = Integer.MIN_VALUE;
        String lessSoldProduct = null;
        int minSales = Integer.MAX_VALUE;


        System.out.println("\n**Weekly Sales Report (Last 7 Days)**:");
        for (Billing bill : allBills) {
            if (bill.getBillDateTime().isAfter(oneWeekAgo)) {
                System.out.println("Bill ID: " + bill.getBillId() + " | Date: " + bill.getBillDateTime());
                billViewer.viewBill(bill.getBillItems());
                weeklySales += billCalculator.totalBill(bill);
                System.out.println("----------------------");
            }

            for (Product product : bill.getBillItems()) {
                productSalesCount.put(product.getName(),
                    productSalesCount.getOrDefault(product.getName(), 0) + product.getStockQuantity());
            }
        }

        for (PurchaseEntry purchases : purchaseEntries) {
            if (purchases.getPurchaseDateTime().isAfter(oneWeekAgo)) {
                weeklyPurchases += (purchases.getProduct().getPurchasePrice() * purchases.getProduct().getStockQuantity());
                System.out.println("----------------------");
            }
        }

        System.out.println("**Total Weekly Sales: ₹" + weeklySales + "**");
        System.out.println("**Total Weekly Expenses: ₹" + weeklyPurchases + "**");
        if (weeklyPurchases > weeklySales){
            System.out.println("**Loss Amount : "+(weeklyPurchases-weeklySales)+"**");
        }
        else {
            System.out.println("**Profit Amount : "+(weeklySales-weeklyPurchases)+"**");
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

        System.out.println("Most Sold Product: " + mostSoldProduct);
        System.out.println("Less Sold Product: " + lessSoldProduct);
    }
}
