import java.util.ArrayList;
import java.util.HashMap;

interface Bill_Operation{
    public void addtoBill(Product product,int quantity);
    public void viewBill();
    public double totalbill();
    public double discountedbill(double total_amount, double discountPercentage);
}


public class Bill implements Bill_Operation {
    private ArrayList<Product> bill_items;
    private static HashMap<Integer,ArrayList<Product>> all_bills = new HashMap<>();
    private int bill_id;

    //Construct for Bill 
    public Bill(int id){
        this.bill_id=id;
        this.bill_items= new ArrayList<Product>();
    }

    //This method adds products to the bill
    @Override
    public void addtoBill(Product product, int quantity){
        if (product.getStockQuantity() > quantity){
            bill_items.add(new Product(product.getName(), product.getPurchasePrice(), product.getMrp(), product.getTaxPercentage(), product.getSellingPrice(), product.getDiscountPercentage(), quantity));
            product.decrease_quantity(quantity);
        }
        else{
            System.out.println("not enough stock");
        }
        
    }

    //this method shows the products in the bill
    @Override
    public void viewBill(){
        for (Product product : bill_items){
            System.out.println("Name : "+product.getName()+ " Quantity : "+product.getStockQuantity());
        }
    }

    //This method return the total amount of bill without discount
    @Override
    public double totalbill(){
        double total_amount = 0;
        for (Product p : bill_items){
            total_amount += (p.getSellingPrice()*p.getStockQuantity());
        }
        return total_amount;
    }

    //This method returns total amount after the discount
    @Override
    public double discountedbill(double total_amount, double discountPercentage){
        double discount_amount = ((total_amount) - ((total_amount*discountPercentage)/100));
        return discount_amount;
    }

    //This method adds the bill along with bill_id to a hashmap for future references
    public void addToBillingData(){
       if (!bill_items.isEmpty()){
            all_bills.put(bill_id,new ArrayList<>(bill_items));
       }
    }

    
}


