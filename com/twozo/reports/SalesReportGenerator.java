package com.twozo.reports;

import java.util.ArrayList;
import java.util.List;

import com.twozo.bill.Billing;
import com.twozo.storage.BillStorage;

public class SalesReportGenerator implements ReportGenerator{
    private final BillStorage billStorage;
    double totalSales = 0;

    public SalesReportGenerator(final BillStorage billStorage) {
        this.billStorage = billStorage;
    }

    @Override
    public Report generateReport() {
        final List<String> reportContents = new ArrayList<>();
        final List<Billing> allBills = billStorage.getAllBills();

        if (allBills.isEmpty()) {
            reportContents.add("\n No sales have been recorded yet.");
            return new Report("** Sales Report **:", reportContents);
        }

        reportContents.add("----------------------");
        for (final Billing bill : allBills) {
            reportContents.add("Bill ID: " + bill.getBillId() + " | Date: " + bill.getBillDateTime());
            totalSales += bill.getDiscountedAmount();
        }

        reportContents.add("----------------------");
        reportContents.add("** Total Sales: " + totalSales + " **");
        return new Report("** Sales Report **:", reportContents);
    }
}
