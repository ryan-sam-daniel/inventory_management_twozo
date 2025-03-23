package com.twozo.purchase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.twozo.finance.ExpensesUpdationService;
import com.twozo.inventory.ProductFinderService;
import com.twozo.inventory.StockManagementService;
import com.twozo.product.Product;
import com.twozo.product.ProductCostCalculationService;
import com.twozo.storage.PurchaseDetailsStorer;

public class PurchaseProcess implements PurchaseService {
    private final PurchaseDetailsStorer purchaseDetailsStorer;
    private final StockManagementService stockManager;
    private final ProductFinderService productFinder;
    private final ExpensesUpdationService expenseDetailsStorer;
    private final ProductCostCalculationService purchaseCostCalculator;
    private final List<Purchase> purchaseList;
    private final LocalDateTime purchaseDateTime = LocalDateTime.now();

    public PurchaseProcess (final PurchaseDetailsStorer purchaseDetailsStorer, final StockManagementService stockManager, final ProductFinderService productFinder, final ExpensesUpdationService expenseDetailsStorer, final ProductCostCalculationService purchaseCostCalculator) {
        this.purchaseDetailsStorer = purchaseDetailsStorer;
        this.stockManager = stockManager;
        this.productFinder = productFinder;
        this.expenseDetailsStorer = expenseDetailsStorer;
        this.purchaseCostCalculator = purchaseCostCalculator;
        this.purchaseList = new ArrayList<>();
    }

    @Override
    public void addProduct (final String name, final int quantity) {
        final Product product = productFinder.findProductByName(name);

        if (product == null) {
            System.out.println("Not found in the inventory");
            return;
        }
        purchaseList.add(new Purchase(new Product(name, product.getPurchasePrice(), product.getMrp(), product.getTaxPercentage(), product.getSellingPrice(), product.getDiscountPercentage(), quantity), purchaseDateTime));
        purchaseDetailsStorer.addPurchaseDetails(purchaseList);
        stockManager.addStock(product, quantity);
        final double expense = purchaseCostCalculator.calculate(product);
        expenseDetailsStorer.addExpense(expense);
        System.out.println("Product Purchased");
    }

}
