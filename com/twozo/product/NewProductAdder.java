package com.twozo.product;

import java.util.Scanner;

import com.twozo.finance.ExpensesUpdationService;
import com.twozo.inventory.InventoryStorage;

public class NewProductAdder implements ProductAddingService {
    private final ExpensesUpdationService expenseDetailsUpdater;
    private final InventoryStorage inventoryItems;
    private final ProductCostCalculationService purchaseCostCalculator;
    private final ProductInputHandler productInputHandler;
    Scanner scanner;

    public NewProductAdder (final ExpensesUpdationService expenseDetailsUpdater,final InventoryStorage inventoryItems, final ProductCostCalculationService purchaseCostCalculator, final ProductInputHandler productInputHandler, Scanner scanner){
        this.expenseDetailsUpdater = expenseDetailsUpdater;
        this.inventoryItems = inventoryItems;
        this.purchaseCostCalculator = purchaseCostCalculator;
        this.productInputHandler = productInputHandler;
        this.scanner = scanner;
    }

    @Override
    public void addProduct(){
        final Product product = productInputHandler.getInputFromUser(scanner);
        inventoryItems.addProductToInventory(product);

        final double purchaseCost = purchaseCostCalculator.calculate(product);
        expenseDetailsUpdater.addExpense(purchaseCost);
        
        System.out.println("Product added to the inventory");
    }
}
