package Inventory_management.finance;

public class FinanceCalculation {
    //Returns expenses from purchasing
    public static double calculate_expenses(double purchasePrice, int quantity){
        return purchasePrice * quantity;
    }

    //returns revenue from sales
    public static double calculate_revenue(double sellingPrice, int quantity, double taxPercentage){
        double revenue = (sellingPrice*quantity);
        double tax_amount = (revenue*taxPercentage)/100;
        return revenue + tax_amount;
    }
}
