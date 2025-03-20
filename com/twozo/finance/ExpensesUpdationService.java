package com.twozo.finance;

public interface ExpensesUpdationService {

    // Getters
    /**
     * Returns the total expenses of the store
     * @return totalExpenses of the store
     */
    double getTotalExpenses();

    /**
     * Add expenses to the store's total expense 
     * @param value total amount from the product purchased
     */
    void addExpense(double value);

}