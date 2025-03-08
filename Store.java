import java.util.Scanner;

public class Store{
    Inventory inventory;
    Purchase purchase;
    Scanner scanner;

        Store(){
            this.inventory = new Inventory();
            this.purchase = new Purchase(inventory);
            this.scanner = new Scanner(System.in);
        }
    
    //this is for store operations
    public void showMenu() {

        //add initial products to inventory
        inventory.addProduct(new Product("Colgate", 5, 10, 2, 15, 0, 10));
        inventory.addProduct(new Product("soap",40,50,18,65,0,10));
        
        
        while (true) {
            System.out.println("1)view Stock\n2)Purchase Stock\n3)View Purchases\n4)Create Bill\n5)Generate Report");
            int option = scanner.nextInt();
            switch (option) {

                //View Stock
                case 1:
                    inventory.displayProducts();
                    break;

                //Purchase Stock
                case 2:
                    System.out.println("Enter the Product Name : ");
                    scanner.nextLine();
                    String p_name = scanner.nextLine();
                    System.out.println("Enter the quantity : ");
                    int p_quantity = scanner.nextInt();
                    purchase.addToList(p_name, p_quantity);
                    break;

                //View Purchases
                case 3:
                    purchase.viewPurchases();
                    break;

                //Create Bill
                case 4:
                System.out.println("Create bill");
                System.out.println("Enter the Phone no :");
                int bill_id = scanner.nextInt();
                Bill bill = new Bill(bill_id);
                double final_amount=0;
                double total_amount=0;
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
                        Product product = inventory.findProduct(bp_name);
                        if(product!=null){
                            bill.addtoBill(product,bp_quant);
                        }
                        else{
                            System.out.println("Product Not found");
                        }
                        break;

                        case 2:
                        bill.viewBill();
                        total_amount=bill.totalbill();
                        System.out.println("Do you want to give any discount : ");
                        int bd = scanner.nextInt();
                        final_amount=bill.discountedbill(total_amount, bd);
                        System.out.println("Final Amount : "+final_amount);
                        break;

                        case 3:
                        System.out.println("Mode of Payment:");
                        System.out.println("1)Cash\n2)UPI\n3)Card(Debit/Credit)");
                        System.out.println("Enter choice :");
                        int option2=scanner.nextInt();
                        switch (option2) {
                            case 1:
                                System.out.println("Thanks for purchasing");
                                bill.addToBillingData();
                                System.out.println(final_amount);
                                Finance.addRevenue(final_amount);
                                break;

                            case 2:
                                System.out.println("Enter Upi id");
                                scanner.nextLine();
                                String upi_id = scanner.nextLine();
                                System.out.println("Transaction Successfull");
                                bill.addToBillingData();
                                Finance.addRevenue(final_amount);
                                break;

                            case 3:
                                System.out.println("Enter card details:");
                                System.out.println("Transaction Successfull");
                                bill.addToBillingData();
                                Finance.addRevenue(final_amount);
                                break;

                            default:
                                System.out.println("Transaction Failed");
                                bill.addToBillingData();
                                Finance.addRevenue(final_amount);
                                break;

                        }
                        break;

                        default:
                        System.out.println("Invalid Choice!");
                        break;
                    }
                }
                break;

                //view report
                case 5:
                    System.out.println("\nTotal Sales : "+Finance.getTotalRevenue());
                    System.out.println("\nTotal Expenses : "+Finance.getTotalExpenses());
                    double pro_loss=Finance.getTotalRevenue() - Finance.getTotalExpenses();
                    if (pro_loss>0){
                        System.out.println("\nProfit : "+pro_loss);
                    }
                    else{
                        System.out.println("\nLoss :" +pro_loss);
                    }
                    System.out.println();
                    System.out.println("Restocking Needs : \n");
                    inventory.lessStockReport();
                break;

                default:
                System.out.println("Invlaid Choice");
                break;
            }
        }
    }
}
