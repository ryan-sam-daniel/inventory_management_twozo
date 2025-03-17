package com.twozo.purchase;

import com.twozo.finance.ValueCalculator;
import com.twozo.inventory.IStockManager;
import com.twozo.inventory.IProductFinder;
import com.twozo.product.Product;
import com.twozo.storage.FinanceDetailsStorer;
import com.twozo.storage.PurchaseDetailsStorer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PurchaseListAdder implements PurchaseService {

    private final PurchaseDetailsStorer purchaseDetailsStorer;
    private final IStockManager stockManager;
    private final IProductFinder productFinder;
    private final FinanceDetailsStorer financeDetailsStorer;

    public PurchaseListAdder (PurchaseDetailsStorer purchaseDetailsStorer, IStockManager stockManager, IProductFinder productFinder, FinanceDetailsStorer financeDetailsStorer) {
        this.purchaseDetailsStorer = purchaseDetailsStorer;
        this.stockManager = stockManager;
        this.productFinder = productFinder;
        this.financeDetailsStorer = financeDetailsStorer;
    }

    @Override
    public void addToList (String name, int quantity, LocalDateTime purchaseDateTime) {
        Product product = productFinder.getProduct(name);

        if (product == null) {
            System.out.println("Not found in the inventory");
            return;
        }

        final List<PurchaseEntry> purchaseList = new ArrayList<>();

        purchaseList.add(new PurchaseEntry(new Product(name, product.getPurchasePrice(), product.getMrp(), product.getTaxPercentage(), product.getSellingPrice(), product.getDiscountPercentage(), quantity), purchaseDateTime));
        purchaseDetailsStorer.addPurchaseDetails(purchaseList);
        stockManager.addStock(product, quantity);
        double expense = ValueCalculator.calculate_expenses(product.getPurchasePrice(), quantity);
        financeDetailsStorer.addExpense(expense);
    }

}
