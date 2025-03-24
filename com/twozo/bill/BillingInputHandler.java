package com.twozo.bill;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.twozo.finance.RevenueUpdationService;
import com.twozo.inventory.InventoryViewerService;
import com.twozo.inventory.ProductFinderService;
import com.twozo.inventory.StockManagementService;
import com.twozo.payment.PaymentMethodHandler;
import com.twozo.payment.PaymentRequest;
import com.twozo.product.Product;
import com.twozo.storage.BillStorage;

public class BillingInputHandler {
    private final BillStorage billStorage;
    private final BillCalculator billCalculator;
    private final InventoryViewerService inventoryViewer;
    private final StockManagementService stockManager;
    private final ProductFinderService productFinder;
    private final RevenueUpdationService revenueDetailsUpdater;

    public BillingInputHandler(BillStorage billStorage,BillCalculator billCalculator
                          , InventoryViewerService inventoryViewer, StockManagementService stockManager
                          , ProductFinderService productFinder, RevenueUpdationService revenueDetailsUpdater) {
        this.billStorage = billStorage;
        this.billCalculator = billCalculator;
        this.inventoryViewer = inventoryViewer;
        this.productFinder = productFinder;
        this.revenueDetailsUpdater = revenueDetailsUpdater;
        this.stockManager = stockManager;
    }

    public void createBill(Scanner scanner) {
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

        final Billing bill = new Bill(billId, stockManager);
        inventoryViewer.viewInventory();
        int choice;

        try {
            while (true) {
                System.out.println("\n1) Add prooduct \n2) Remove product \n3) Paymnet");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                    System.out.println("Enter the product name : ");
                    scanner.nextLine();
                    String productChoice = scanner.nextLine();
                    int quantity;

                    do{
                        System.out.println("Enter quantity:");
                        quantity = scanner.nextInt();
                        if (quantity < 1)
                        System.out.println("Quantity should be positive");
                    }while(quantity < 1) ;

                    Product selectedProduct = productFinder.findProductByName(productChoice);
                    if (selectedProduct != null) {
                        bill.addProductToBill(selectedProduct, quantity);
                        System.out.println("Product Added successfully ! ");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                        break;

                    case 2:
                    System.out.println("Enter the product name : ");
                    scanner.nextLine();
                    productChoice = scanner.nextLine();

                    do{
                        System.out.println("Enter quantity:");
                        quantity = scanner.nextInt();
                        if (quantity < 1)
                        System.out.println("Quantity should be positive");
                    }while(quantity < 1) ;

                    selectedProduct = productFinder.findProductByName(productChoice);
                    if (selectedProduct != null) {
                        bill.removeProductFromBill(selectedProduct, quantity);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                    case 3:
                    System.out.println("Bill created successfully!");
                    final double total = billCalculator.totalBillAmount(bill);
                    System.out.println("Total Bill Amount: " + total);
                    System.out.println("Do you want any discount : ");
                    final int discount = scanner.nextInt();
                    final double final_amount = billCalculator.discountedBillAmount(total, discount);
                    System.out.println("Final Amount : "+ final_amount);
                    bill.setTotalAmount(total);
                    bill.setDiscountedAmount(final_amount);
                    if (final_amount == 0){
                        System.out.println("No need to pay");
                        break;
                    }
                    final PaymentRequest paymentRequest = new PaymentRequest(billId,final_amount);
                    final PaymentMethodHandler paymentMethodHandler = new PaymentMethodHandler(paymentRequest);
                    paymentMethodHandler.chooseMethod();
                    billStorage.addToBillStorage(bill);
                    revenueDetailsUpdater.addRevenue(final_amount);
                    break;

                    default :
                    System.out.println("Invalid choice");
                    break;
                }
                if (choice == 3){
                    break;
                }
            }
        } catch (final InputMismatchException e) {
            System.out.println("Invalid input ! enter a number ");
            choice = scanner.nextInt();
        }
    }
}
