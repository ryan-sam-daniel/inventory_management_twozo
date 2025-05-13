package com.twozo.app.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Report {
    String maxSold;
    String minSold;
    double weekSale;
    double weekPurchase;
    HashMap<Integer,Product> lowStockItems;
    double totalSale;
    double totalPurchase;

    public Report(){}

    public Report(String maxSold, String minSold, double weekSale, double weekPurchase, HashMap<Integer, Product> lowStockItems, double totalSale, double totalPurchase){
        this.maxSold = maxSold;
        this.minSold = minSold;
        this.weekSale = weekSale;
        this.weekPurchase = weekPurchase;
        this.lowStockItems = lowStockItems;
        this.totalSale = totalSale;
        this.totalPurchase = totalPurchase;
    }

    public String getMaxSold() {
        return maxSold;
    }
    public void setMaxSold(String maxSold) {
        this.maxSold = maxSold;
    }
    public HashMap<Integer,Product> getLowStockItems() {
        return lowStockItems;
    }
    public void setLowStockItems(HashMap<Integer, Product> lowStockItems) {
        this.lowStockItems = lowStockItems;
    }
    public String getMinSold() {
        return minSold;
    }
    public void setMinSold(String minSold) {
        this.minSold = minSold;
    }
    public double getWeekSale() {
        return weekSale;
    }
    public void setWeekSale(double weekSale) {
        this.weekSale = weekSale;
    }
    public double getWeekPurchase() {
        return weekPurchase;
    }
    public void setWeekPurchase(double weekPurchase) {
        this.weekPurchase = weekPurchase;
    }
    public double getTotalSale() {
        return totalSale;
    }
    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }
    public double getTotalPurchase() {
        return totalPurchase;
    }
    public void setTotalPurchase(double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
}
