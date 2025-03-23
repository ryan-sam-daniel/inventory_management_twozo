package com.twozo.bill;

import java.util.List;
import java.util.Scanner;

import com.twozo.storage.BillStorage;

public class BillViewerService {
    private final BillPrinter billPrinter;
    private final BillStorage billStorage;

    public BillViewerService(BillPrinter billPrinter, BillStorage billStorage){
        this.billPrinter = billPrinter;
        this.billStorage = billStorage;
    }

    public void viewBill(Scanner scanner){
        System.out.println("Enter phone_no to view:");
        final int viewBillId = scanner.nextInt();
        final List<Billing> retrievedBill = billStorage.getBill(viewBillId);

        if (retrievedBill.isEmpty()){
            System.out.println("No bill found");
            return;
        }

        for ( final Billing bills : retrievedBill){
            if (retrievedBill != null) {
                System.out.println("Bill Date & Time: " + bills.getBillDateTime());
                billPrinter.showBill(bills.getBillItems());
                System.out.println("Total Amount : " + bills.getTotalAmount());
                System.out.println("Total Amount After Discount : "+bills.getDiscountedAmount());
            } else {
                System.out.println("Bill not found.");
            }
        }
        
    }    
}
