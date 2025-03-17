package com.twozo.main;

import java.util.List;
import java.util.Scanner;
import com.twozo.inventory.*;
import com.twozo.payment.PaymentHandler;
import com.twozo.product.*;
import com.twozo.purchase.*;
import com.twozo.storage.*;
import com.twozo.bill.*;
import com.twozo.reports.*;

public class Main {
    public static void main(String[] args) {
        InventoryItems inventoryItems = new InventoryItems();

        // Add initial products to inventory
        inventoryItems.addProduct(new Product("Colgate", 5, 10, 2, 15, 0, 10));
        inventoryItems.addProduct(new Product("Soap", 40, 50, 18, 65, 0, 10));

        // Dependency Injection for different modules
        IProductFinder productFinder = new InventoryProductFinder(inventoryItems);
        IStockManager stockManager = new StockManager();
        PurchaseDetailsStorer purchaseDetailsStorer = new PurchaseDetailsStorer();
        FinanceDetailsStorer financeDetailsStorer = new FinanceDetailsStorer();
        PurchaseService purchasesListAdder = new PurchaseListAdder(purchaseDetailsStorer, stockManager, productFinder, financeDetailsStorer);
        StockPurchaseHandler stockBuyer = new StockPurchaseHandler(purchasesListAdder, productFinder);
        InventoryViewer inventoryViewer = new InventoryViewer(inventoryItems);
        PurchasedProductsViewer purchasedProductsViewer = new PurchasedProductsViewer(purchaseDetailsStorer);
        BillStorage billStorage = new BillStorage();
        BillCalculator billCalculator = new BillCalculator();
        BillViewer billViewer = new BillViewer();
        PaymentHandler paymentHandler = new PaymentHandler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1) View Stock \n2) Purchase Stock \n3) View Purchases \n4) Create Bill \n5) View Bill \n6) Generate Sales Report \n7) Generate Weekly Report \n8) Generate Stock Report \n9) Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                inventoryViewer.displayProducts();
                break;

                case 2:
                stockBuyer.purchaseStock();
                break;

                case 3:
                purchasedProductsViewer.viewPurchases();
                break;

                case 4:
                System.out.println("Enter Bill ID:");
                int billId = scanner.nextInt();
                Billing bill = new Bill(billId, stockManager);

                while (true) {
                    System.out.println("Select product: \n1) Colgate \n2) Soap \n3) Finish");
                    int productChoice = scanner.nextInt();
                    if (productChoice == 3) break;

                    System.out.println("Enter quantity:");
                    int quantity = scanner.nextInt();

                    Product selectedProduct = (productChoice == 1) ? productFinder.getProduct("Colgate") :
                            (productChoice == 2) ? productFinder.getProduct("Soap") : null;

                    if (selectedProduct != null) {
                        bill.addProductToBill(selectedProduct, quantity);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }

                billStorage.addToBillStorage(bill);
                System.out.println("Bill created successfully!");
                double total = billCalculator.totalBill(bill);
                System.out.println("Total Bill Amount: " + total);
                System.out.println("Do you want any discount : ");
                int discount = scanner.nextInt();
                double final_amount = billCalculator.discountedBill(total, discount);
                System.out.println("Final Amount : "+ final_amount);
                financeDetailsStorer.addRevenue(final_amount);
                paymentHandler.pay();
                break;

                case 5:
                System.out.println("Enter Bill ID to view:");
                int viewBillId = scanner.nextInt();
                List<Billing> retrievedBill = billStorage.getBill(viewBillId);

                for ( Billing bills : retrievedBill){
                    if (retrievedBill != null) {
                        System.out.println("Bill Date & Time: " + bills.getBillDateTime());
                        billViewer.viewBill(bills.getBillItems());
                    } else {
                        System.out.println("Bill not found.");
                    }
                }
                break;

                case 6:
                ReportGenerator salesReport = new SalesReportGenerator(billStorage);
                salesReport.generateReport();
                break;

                case 7:
                ReportGenerator weeklyReport = new WeeklySalesReportGenerator(billStorage, purchaseDetailsStorer, billViewer, billCalculator);
                weeklyReport.generateReport();
                break;

                case 8:
                ReportGenerator stockReport = new StockReportGenerator(inventoryItems);
                stockReport.generateReport();
                break;

                case 9:
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
        }
    }
}
