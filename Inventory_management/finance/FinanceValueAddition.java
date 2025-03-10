package Inventory_management.finance;

public class FinanceValueAddition {
    public static void addExpense(double value){
        double total_expenses = Finance.getTotalExpenses();
        total_expenses += value;
        Finance.setTotalExpenses(total_expenses);
    }

    //add revenue to total_revenues
    public static void addRevenue(double value){
        double total_revenues = Finance.getTotalRevenue();
        total_revenues += value;
        Finance.setTotalRevenue(total_revenues);
    }
}
