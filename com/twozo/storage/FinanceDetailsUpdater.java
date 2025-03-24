package com.twozo.storage;

import com.twozo.finance.ExpensesUpdationService;
import com.twozo.finance.RevenueUpdationService;

public class FinanceDetailsUpdater implements RevenueUpdationService, ExpensesUpdationService{
    private double totalExpenses;
    private double totalRevenue;

    public FinanceDetailsUpdater() {
        this.totalExpenses = 0;
        this.totalRevenue = 0;
    }

    /**
     * Returns the total expenses of the store
     * @return totalExpenses of the store
     */
    @Override
    public double getTotalExpenses() {
        return totalExpenses;
    }

    /**
     * Returns the total revenue that are from store sales
     * @return totalRevenue of the store
     */
    @Override
    public double getTotalRevenue() {
        return totalRevenue;
    }


    /**
     * Add expenses to the store's total expense 
     * @param value total amount from the product purchased
     */
    @Override
    public void addExpense(final double value) {
        totalExpenses += value;
    }

    
    /**
     * Add revenue to the store's total revenue
     * @param value total amount from the product sales
     */
    @Override
    public void addRevenue(final double value) {
        totalRevenue += value;
    }
}
