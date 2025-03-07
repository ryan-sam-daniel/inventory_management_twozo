import java.util.ArrayList;

public class Store {
    static public ArrayList<Product> inventory = new ArrayList<>();
    static public ArrayList<Product> purchase_list = new ArrayList<>();
    public static double expense = 0;
    public static double revenue = 0;
    public static double purchases =0;
    public void addproduct(Product product){
        inventory.add(product);
        expense+=product.totalprice();
    }
    public void displayStock(){
        for (Product p : inventory){
            p.displayProduct();
        }
    }
    public Product findStock(String name){
        for (Product p : inventory){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }
    
    public void Purchase(String name, int quantity){
        Product product = findStock(name);
        product.increaseStock(quantity);
        Product purchase_Product = new Product(
            product.name,
            product.purchasePrice,
            product.mrp,
            product.taxPercentage,
            product.sellingPrice,
            product.discountPercentage,
            quantity  
        );
        purchase_list.add(purchase_Product);
        expense+=product.purchasePrice*quantity;
        purchases+=product.purchasePrice*quantity;
    }
    public void reduceStock_needs(){
        for ( Product p : inventory){
            if (p.stockQuantity < 5){
                System.out.println(p.name+" needs to be purchased");
            }
        }
    }
    public void view_purchases(){
        if (purchase_list.isEmpty()){
            System.out.println("No purchasees happened");
        }
        else{
        for (Product p : purchase_list){
            System.out.println("Name - "+p.name+"\tQuantity - "+p.stockQuantity);
        }
    }
    }

}
