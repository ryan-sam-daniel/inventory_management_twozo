package com.twozo.bill;

import java.util.List;
import java.util.Scanner;

import com.twozo.storage.BillStorage;

public class BillViewer implements BillViewerService {
    private final BillPrinter billPrinter;
    private final BillStorage billStorage;

    public BillViewer(BillPrinter billPrinter, BillStorage billStorage){
        this.billPrinter = billPrinter;
        this.billStorage = billStorage;
    }

    public void viewBill(Scanner scanner){
        long billId;
        int length;
        do{
            System.out.println("Enter Phone_no ID:");
            billId = scanner.nextLong();
            length =  String.valueOf(billId).length();
            if (length == 10 ){
                break;
            }
            System.out.println("Invalid bill ID: It must be a 10-digit number.");
        }while(length != 10);
        final List<Billing> retrievedBill = billStorage.getBill(billId);

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
