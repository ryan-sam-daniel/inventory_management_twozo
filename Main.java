import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Store store = new Store();
        Scanner scanner = new Scanner(System.in);
        store.addproduct(new Product("Colgate", 5, 10, 2, 15, 0, 10));
        store.addproduct(new Product("soap",40,50,18,65,0,10));
        while (true) {
            System.out.println("\n1. View Stock\n2. Purchase History\n3. Purchase Stock\n4. Create Bill\n5. Generate Report\n6. Customer Sales Report");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    store.displayStock();
                    break;
                case 2:
                    store.view_purchases();
                    break;
                case 3:
                    System.out.println("Enter the product name ");
                    scanner.nextLine();
                    String pp_name = scanner.nextLine();
                    System.out.println("Enter the quantity;");
                    int pp_quant = scanner.nextInt();
                    store.Purchase(pp_name, pp_quant);
                    break;
                case 4:
                    System.out.println("Create bill");
                    Bill bill = new Bill();
                    int opt=0;
                    while (opt!=4){
                        System.out.println("1)Add product\n2)View Total\n3)Payment\n4)Exit");
                        opt = scanner.nextInt();
                        switch (opt) {
                            case 1:
                            System.out.println("Enter the product name");
                            scanner.nextLine();
                            String bp_name = scanner.nextLine();
                            System.out.println("Enter the quantity");
                            int bp_quant=scanner.nextInt();
                            if(!bill.addTobill(bp_name, bp_quant)){  
                                break;
                            }
                            break;
                            case 2:
                            bill.findTotal();
                            System.out.println("Do you want to give any discount : ");
                            int bd = scanner.nextInt();
                            bill.giveDiscount(bd);
                            break;
                            case 3:
                            System.out.println("Enter you phone number :");
                            int ph_no=scanner.nextInt();
                            bill.addTostorage(ph_no);
                            System.out.println("Mode of Payment:");
                            System.out.println("1)Cash\n2)UPI\n3)Card(Debit/Credit)");
                            System.out.println("Enter choice :");
                            int option=scanner.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.println("Thanks for purchasing");
                                    bill.addrevenue();
                                    break;
                                case 2:
                                    System.out.println("Enter Upi id");
                                    scanner.nextLine();
                                    String upi_id = scanner.nextLine();
                                    System.out.println("Transaction Successfull");
                                    bill.addrevenue();
                                    break;
                                case 3:
                                    System.out.println("Enter card details:");
                                    System.out.println("Transaction Successfull");
                                    bill.addrevenue();
                                    break;
                                default:
                                    System.out.println("Transaction Failed");
                                    bill.addrevenue();
                                    break;
                            }
                                    break;
                            default:
                                break;
                        }
                    }
                    
                break;
                case 5:
                    System.out.println("\nTotal Sales : "+Store.revenue);
                    System.out.println("\nTotal Expenses : "+Store.expense);
                    double pro_loss=Store.revenue - Store.expense;
                    if (pro_loss>0){
                        System.out.println("\nProfit : "+pro_loss);
                    }
                    else{
                        System.out.println("\nLoss :" +pro_loss);
                    }
                    System.out.println("\nProfit per Product :");
                    for (Product p : Store.inventory){
                        System.out.println("Product : "+p.name+"  Profit : "+(p.sellingPrice-p.purchasePrice));
                    }
                    System.out.println("\nProduct Sale report :");
                    Bill.productSales();
                    System.out.println();
                    System.out.println("Restocking Needs : \n");
                    store.reduceStock_needs();
                break;
                case 6:
                    System.out.println("Enter you phone number :");
                    int cust_ph_no=scanner.nextInt();
                    Bill.show_storage(cust_ph_no);
                    break;
            }
}
    }
}
