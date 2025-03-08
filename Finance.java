
public class Finance {
    private static double total_expenses = 0;
    private static double total_revenues = 0;


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

    //add expense to total_expenses
    public static void addExpense(double value){
        total_expenses += value;
    }

    //add revenue to total_revenues
    public static void addRevenue(double value){
        total_revenues += value;
    }

    //get total expenses
    public static double getTotalExpenses() {
        return total_expenses;
    }

    //get total revenues
    public static double getTotalRevenue(){
        return total_revenues;
    }
}
