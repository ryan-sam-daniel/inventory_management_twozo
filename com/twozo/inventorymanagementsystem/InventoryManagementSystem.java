package com.twozo.inventorymanagementsystem;

import java.util.Scanner;

import com.twozo.bill.BillCalculator;
import com.twozo.bill.BillPrinter;
import com.twozo.bill.BillViewer; 
import com.twozo.bill.BillViewerService;
import com.twozo.bill.BillingInputHandler;
import com.twozo.finance.ExpensesUpdationService;
import com.twozo.finance.RevenueUpdationService;
import com.twozo.inventory.InventoryItems;
import com.twozo.inventory.InventoryProductFinder;
import com.twozo.inventory.InventoryStorage;
import com.twozo.inventory.InventoryViewer;
import com.twozo.inventory.InventoryViewerService;
import com.twozo.inventory.ProductFinderService;
import com.twozo.inventory.StockManagementService;
import com.twozo.inventory.StockManager;
import com.twozo.product.NewProductAdder;
import com.twozo.product.Product;
import com.twozo.product.ProductAddingService;
import com.twozo.product.ProductCostCalculationService;
import com.twozo.product.ProductInputHandler;
import com.twozo.product.ProductPurchaseCost;
import com.twozo.purchase.PurchaseProcess;
import com.twozo.purchase.PurchaseService;
import com.twozo.purchase.PurchasedProductsViewer;
import com.twozo.purchase.StockPurchaseInputHandler;
import com.twozo.reports.Report;
import com.twozo.reports.ReportGenerator;
import com.twozo.reports.SalesReportGenerator;
import com.twozo.reports.StockReportGenerator;
import com.twozo.reports.WeeklySalesReportGenerator;
import com.twozo.storage.BillStorage;
import com.twozo.storage.FinanceDetailsUpdater;
import com.twozo.storage.PurchaseDetailsStorer;

public class InventoryManagementSystem { 
    public static void main(final String[] args) {
        final InventoryStorage inventoryItems = new InventoryItems();
        final Scanner scanner = new Scanner(System.in);
        final PurchaseDetailsStorer purchaseDetailsStorer = new PurchaseDetailsStorer();
        final ExpensesUpdationService expenseDetailsUpdater = new FinanceDetailsUpdater();
        final RevenueUpdationService revenueDetailsUpdater= new FinanceDetailsUpdater();
        final ProductCostCalculationService purchaseCostCalculator = new ProductPurchaseCost();
        final ProductInputHandler productInputHandler = new ProductInputHandler();
        final ProductAddingService newProductAdder = new NewProductAdder(expenseDetailsUpdater,inventoryItems,purchaseCostCalculator,productInputHandler,scanner);
        
        // Add initial products to inventory
        inventoryItems.addProductToInventory(new Product("Colgate", 5, 10, 2, 15, 0, 10));
        inventoryItems.addProductToInventory(new Product("Soap", 40, 50, 18, 65, 0, 10));

        // Dependency Injection for different modules
        final ProductFinderService productFinder = new InventoryProductFinder(inventoryItems);
        final StockManagementService stockManager = new StockManager();
        final InventoryViewerService inventoryViewer = new InventoryViewer(inventoryItems);
        final PurchasedProductsViewer purchasedProductsViewer = new PurchasedProductsViewer(purchaseDetailsStorer,purchaseCostCalculator);
        final BillStorage billStorage = new BillStorage();
        final BillCalculator billCalculator = new BillCalculator();
        final BillPrinter billPrinter = new BillPrinter();
        final BillingInputHandler billingInputHandler = new BillingInputHandler(billStorage, billCalculator
                          ,  inventoryViewer,  stockManager, productFinder, revenueDetailsUpdater);
        final BillViewerService billViewerService = new BillViewer(billPrinter, billStorage);
        
        while (true) {
            System.out.println("\n1) View Stock \n2) Purchase Stock \n3) View Purchases \n4) Create Bill \n5) View Bill \n6) Generate Sales Report \n7) Generate Weekly Report \n8) Generate Stock Report \n9) Add New Product \n10) Exit");
            final int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (option) {
                case 1:
                inventoryViewer.viewInventory();
                break;   

                case 2:
                final PurchaseService purchasesprocess = new PurchaseProcess(purchaseDetailsStorer, stockManager, productFinder, expenseDetailsUpdater, purchaseCostCalculator);
                final StockPurchaseInputHandler stockBuyer = new StockPurchaseInputHandler(purchasesprocess);
                stockBuyer.purchaseStock();
                break;

                case 3:
                purchasedProductsViewer.viewPurchases();
                break;

                case 4:
                billingInputHandler.createBill(scanner);
                break;

                case 5:
                billViewerService.viewBill(scanner);
                break;

                case 6:
                final ReportGenerator salesReporter = new SalesReportGenerator(billStorage);
                final Report salesReport = salesReporter.generateReport();
                salesReport.reportPrinter();
                System.out.println("Overall Expenses : "+expenseDetailsUpdater.getTotalExpenses());
                System.out.println("Overall Revenue : "+revenueDetailsUpdater.getTotalRevenue());
                break;

                case 7:
                final ReportGenerator weeklyReporter = new WeeklySalesReportGenerator(billStorage, purchaseDetailsStorer);
                final Report weekReport = weeklyReporter.generateReport();
                weekReport.reportPrinter();
                break;

                case 8:
                final ReportGenerator stockReporter = new StockReportGenerator(inventoryItems);
                final Report stockReport = stockReporter.generateReport();
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
