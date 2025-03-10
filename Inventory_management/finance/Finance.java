package Inventory_management.finance;

public class Finance {
    private static double total_expenses = 0;
    private static double total_revenues = 0;

    //get total expenses
    public static double getTotalExpenses() {
        return total_expenses;
    }

    //get total revenues
    public static double getTotalRevenue(){
        return total_revenues;
    }

    //get total expenses
    public static void setTotalExpenses(double value) {
        total_expenses = value;
    }

    //get total revenues
    public static void setTotalRevenue(double value){
        total_revenues = value;
    }
}
