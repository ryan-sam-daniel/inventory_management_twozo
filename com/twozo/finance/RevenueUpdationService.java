package com.twozo.finance;

public interface RevenueUpdationService {

    /**
     * Returns the total revenue that are from store sales
     * @return totalRevenue of the store
     */
    double getTotalRevenue();

    /**
     * Add revenue to the store's total revenue
     * @param value total amount from the product sales
     */
    void addRevenue(double value);

}