package com.twozo.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.twozo.bill.Bill;
import com.twozo.bill.BillCalculator;
import com.twozo.bill.BillViewer;
import com.twozo.bill.BillingService;
import com.twozo.finance.ExpensesUpdationService;
import com.twozo.finance.RevenueUpdationService;
import com.twozo.inventory.InventoryItems;
import com.twozo.inventory.InventoryProductFinder;
import com.twozo.inventory.InventoryViewer;
import com.twozo.inventory.InventoryViewerService;
import com.twozo.inventory.ProductFinderService;
import com.twozo.inventory.StockManagementService;
import com.twozo.inventory.StockManager;
import com.twozo.payment.PaymentHandler;
import com.twozo.product.NewProductAdder;
import com.twozo.product.Product;
import com.twozo.product.ProductAddingServices;
import com.twozo.product.ProductCostCalculationService;
import com.twozo.product.ProductPurchaseCost;
import com.twozo.purchase.PurchaseProcess;
import com.twozo.purchase.PurchaseService;
import com.twozo.purchase.PurchasedProductsViewer;
import com.twozo.purchase.StockPurchaseHandler;
import com.twozo.reports.Report;
import com.twozo.reports.ReportGenerator;
import com.twozo.reports.SalesReportGenerator;
import com.twozo.reports.StockReportGenerator;
import com.twozo.reports.WeeklySalesReportGenerator;
import com.twozo.storage.BillStorage;
import com.twozo.storage.FinanceDetailsStorer;
import com.twozo.storage.PurchaseDetailsStorer;

public class InventoryManagementSystem { 
    public static void main(String[] args) {
        final InventoryItems inventoryItems = new InventoryItems();
        PurchaseDetailsStorer purchaseDetailsStorer = new PurchaseDetailsStorer();
        ExpensesUpdationService expenseDetailsStorer = new FinanceDetailsStorer();
        RevenueUpdationService revenueDetailsStorer = new FinanceDetailsStorer();
        ProductCostCalculationService purchaseCostCalculator = new ProductPurchaseCost();
        ProductAddingServices newProductAdder = new NewProductAdder(expenseDetailsStorer,inventoryItems,purchaseCostCalculator);
        
        // Add initial products to inventory
        inventoryItems.addProduct(new Product("Colgate", 5, 10, 2, 15, 0, 10));
        inventoryItems.addProduct(new Product("Soap", 40, 50, 18, 65, 0, 10));

        // Dependency Injection for different modules
        ProductFinderService productFinder = new InventoryProductFinder(inventoryItems);
        StockManagementService stockManager = new StockManager();
        InventoryViewerService inventoryViewer = new InventoryViewer(inventoryItems);
        PurchasedProductsViewer purchasedProductsViewer = new PurchasedProductsViewer(purchaseDetailsStorer,purchaseCostCalculator);
        BillStorage billStorage = new BillStorage();
        BillCalculator billCalculator = new BillCalculator();
        BillViewer billViewer = new BillViewer();
        PaymentHandler paymentHandler = new PaymentHandler();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1) View Stock \n2) Purchase Stock \n3) View Purchases \n4) Create Bill \n5) View Bill \n6) Generate Sales Report \n7) Generate Weekly Report \n8) Generate Stock Report \n9) Add New Product \n10) Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                inventoryViewer.viewInventory();
                break;   

                case 2:
                PurchaseService purchasesListAdder = new PurchaseProcess(purchaseDetailsStorer, stockManager, productFinder, expenseDetailsStorer, purchaseCostCalculator);
                StockPurchaseHandler stockBuyer = new StockPurchaseHandler(purchasesListAdder);
                stockBuyer.purchaseStock();
                break;

                case 3:
                purchasedProductsViewer.viewPurchases();
                break;

                case 4:
                System.out.println("Enter Phone_no ID:");
                int billId = scanner.nextInt();
                BillingService bill = new Bill(billId, stockManager);
                inventoryViewer.viewInventory();
                int choice;
                try {
                    while (true) {
                        System.out.println("\n1) Add prooduct \n2) Paymnet");
                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                            System.out.println("Enter the product name : ");
                            scanner.nextLine();
                            String productChoice = scanner.nextLine();
                            System.out.println("Enter quantity:");
                            int quantity = scanner.nextInt();
                            Product selectedProduct = productFinder.getProduct(productChoice);
                            if (selectedProduct != null) {
                                bill.addProductToBill(selectedProduct, quantity);
                            } else {
                                System.out.println("Invalid choice.");
                            }
                                break;
    
                            case 2:
                            System.out.println("Bill created successfully!");
                            double total = billCalculator.totalBillAmount(bill);
                            System.out.println("Total Bill Amount: " + total);
                            System.out.println("Do you want any discount : ");
                            int discount = scanner.nextInt();
                            double final_amount = billCalculator.discountedBillAmount(total, discount);
                            System.out.println("Final Amount : "+ final_amount);
                            bill.setTotalAmount(total);
                            bill.setDiscountedAmount(final_amount);
                            paymentHandler.pay();
                            billStorage.addToBillStorage(bill);
                            revenueDetailsStorer.addRevenue(final_amount);
                            break;
    
                            default :
                            System.out.println("Invalid choice");
                            break;
                        }
                        if (choice == 2){
                            break;
                        }
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input ! enter a number ");
                    choice = scanner.nextInt();
                }
                
                case 5:
                System.out.println("Enter phone_no to view:");
                int viewBillId = scanner.nextInt();
                List<BillingService> retrievedBill = billStorage.getBill(viewBillId);
                if (retrievedBill.isEmpty()){
                    System.out.println("No bill found");
                    break;
                }
                for ( BillingService bills : retrievedBill){
                    if (retrievedBill != null) {
                        System.out.println("Bill Date & Time: " + bills.getBillDateTime());
                        billViewer.viewBill(bills.getBillItems());
                        System.out.println("Total Amount : " + bills.getTotalAmount());
                        System.out.println("Total Amount After Discount : "+bills.getDiscountedAmount());
                    } else {
                        System.out.println("Bill not found.");
                    }
                }
                break;

                case 6:
                ReportGenerator salesReporter = new SalesReportGenerator(billStorage);
                Report salesReport = salesReporter.generateReport();
                salesReport.reportPrinter();
                System.out.println("Overall Expenses : "+expenseDetailsStorer.getTotalExpenses());
                System.out.println("Overall Revenue : "+revenueDetailsStorer.getTotalRevenue());
                break;

                case 7:
                ReportGenerator weeklyReporter = new WeeklySalesReportGenerator(billStorage, purchaseDetailsStorer);
                Report weekReport = weeklyReporter.generateReport();
                weekReport.reportPrinter();
                break;

                case 8:
                ReportGenerator stockReporter = new StockReportGenerator(inventoryItems);
                Report stockReport = stockReporter.generateReport();
                stockReport.reportPrinter();
                break;

                case 9:
                System.out.println("Adding New Product into System ");
                newProductAdder.addProduct();
                break;

                case 10:
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
        }
    }
}
