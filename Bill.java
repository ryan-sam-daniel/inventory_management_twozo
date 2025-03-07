import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Bill {
    ArrayList<Product> bill_items = new ArrayList<>(); 
    static HashMap<String,Integer> sales = new HashMap<>();
    static HashMap<Integer,Double> cust_detials = new HashMap<>();
    int id;
    float totalprice=0;
    float final_price=0;
    int discount;
    public Bill(){
            this.bill_items=new ArrayList<>();
            this.totalprice=0;
            this.discount=0;
    }
    public boolean addTobill(String name,int quantity){
        Product product = null;
        for (Product p : Store.inventory) {  
            if (p.getName().equalsIgnoreCase(name)) {
                product = p;
                break;
            }
        }
        if (product.stockQuantity < quantity){
            System.out.println("Not enough stock to buy");
            return false;
        }
        else{
        product.reduceStock(quantity);
        Product billProduct = new Product(
            product.name,
            product.purchasePrice,
            product.mrp,
            product.taxPercentage,
            product.sellingPrice,
            product.discountPercentage,
            quantity  
        );
        bill_items.add(billProduct);
        return true;
    }
}
    public void findTotal(){
        totalprice=0;
        for (Product p : bill_items){
            totalprice+=(p.sellingPrice+((p.sellingPrice*p.taxPercentage)/100))*p.stockQuantity;
        }
        System.out.println("Final Bill : "+ totalprice);
        for (Product p : bill_items){
            sales.put(p.name,sales.getOrDefault(p.name, 0)+p.stockQuantity);
        }

    }
    public void giveDiscount(int discount){
        final_price=0;
        final_price = totalprice - ((totalprice*discount)/100);
        System.out.println("Final Bill after Discount : "+final_price);
    }
    public void addrevenue(){
        Store.revenue+=final_price;
    }

    public static void productSales(){
        for (Map.Entry<String,Integer> entry : sales.entrySet()){
            System.out.println("Name : "+(entry.getKey())+"   Quantity : "+entry.getValue());
        }
    }
    public void addTostorage(int ph_no){
        cust_detials.put(ph_no, cust_detials.getOrDefault(ph_no, 0.0) + totalprice);
       
    }
    public static void show_storage(int ph_no){
        for (Map.Entry<Integer,Double> entry : cust_detials.entrySet()){
            if (entry.getKey() == ph_no){
                System.out.println("Customer number : "+ph_no+" Total Purchases : "+entry.getValue());
            }
        }
    }
    
}
