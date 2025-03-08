import java.util.ArrayList;

public class Inventory {
    private ArrayList <Product> inventory_list;

    //this constructor creates a list that can have Product objects in it
    public Inventory(){
        this.inventory_list = new ArrayList<>();
    }

    //this method adds product objects to the inventory list
    public void addProduct(Product product){
        inventory_list.add(product);
    }

    //this method gets each product from list and calls the display method from product class
    public void displayProducts(){
        for (Product p : inventory_list){
            p.display_product();
        }
    }

    //this method finds the product in the list and returns the product
    public Product findProduct(String name){
        for (Product p : inventory_list){
            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        return null;
    }

    //This shows stocks that has less quantity than a threshold value of 5
    public void lessStockReport(){
        for (Product p : inventory_list){
            if(p.getStockQuantity() < 5){
                System.out.println("Name : "+p.getName()+ "has less stocks at minimum level");
            }
        }
    }

}
