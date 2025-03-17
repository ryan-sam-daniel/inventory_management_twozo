package com.twozo.storage;

public class FinanceDetailsStorer{
    private double totalExpenses = 0;
    private double totalRevenue = 0;

    // Getters
    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Add expenses instead of replacing value
    public void addExpense(double value) {
        totalExpenses += value;
    }

    // Add revenue instead of replacing value
    public void addRevenue(double value) {
        totalRevenue += value;
    }
}
