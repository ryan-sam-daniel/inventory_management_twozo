package com.twozo.reports;

import com.twozo.storage.BillStorage;
import com.twozo.bill.*;
import java.util.List;

public class SalesReportGenerator implements ReportGenerator{
    private final BillStorage billStorage;

    public SalesReportGenerator(BillStorage billStorage) {
        this.billStorage = billStorage;
    }

    @Override
    public void generateReport() {
        final List<Billing> allBills = billStorage.getAllBills();
        double totalSales = 0;

        if (allBills.isEmpty()) {
            System.out.println("\n No sales have been recorded yet.");
            return;
        }

        System.out.println("\n**Sales Report**:");

        for (Billing bill : allBills) {
            System.out.println("Bill ID: " + bill.getBillId() + " | Date: " + bill.getBillDateTime());
            BillViewer billViewer = new BillViewer();
            BillCalculator billCalculator = new BillCalculator();
            billViewer.viewBill(bill.getBillItems());
            totalSales += billCalculator.totalBill(bill);
            System.out.println("----------------------");
        }

        System.out.println("**Total Sales: " + totalSales + "**");
    }
}
