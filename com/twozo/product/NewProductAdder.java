package com.twozo.product;

import java.util.Scanner;

import com.twozo.finance.ExpensesUpdationService;
import com.twozo.inventory.InventoryStorage;

public class NewProductAdder implements ProductAddingService {
    private final ExpensesUpdationService expenseDetailsStorer;
    private final InventoryStorage inventoryItems;
    private final ProductCostCalculationService purchaseCostCalculator;
    private final ProductInputHandler productInputHandler;
    Scanner scanner;

    public NewProductAdder (final ExpensesUpdationService expenseDetailsStorer,final InventoryStorage inventoryItems, final ProductCostCalculationService purchaseCostCalculator, final ProductInputHandler productInputHandler, Scanner scanner){
        this.expenseDetailsStorer = expenseDetailsStorer;
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
        expenseDetailsStorer.addExpense(purchaseCost);
        
        System.out.println("Product added to the inventory");
    }
}
