interface ProductOperations {
    void displayProduct();
    void increaseQuantity(int quantity);
    void decreaseQuantity(int quantity);
}

public class Product {
    private String name;
    private double purchasePrice;
    private double mrp;
    private double taxPercentage;
    private double sellingPrice;
    private double discountPercentage;
    private int stockQuantity;

    public Product(String name, double purchasePrice, double mrp, double taxPercentage, 
                   double sellingPrice, double discountPercentage, int stockQuantity) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.mrp = mrp;
        this.taxPercentage = taxPercentage;
        this.sellingPrice = sellingPrice;
        this.discountPercentage = discountPercentage;
        this.stockQuantity = stockQuantity;
    }

    //This method is for display the product
    public void display_product(){
        System.out.println("Product : "+name+"  Quantity : "+stockQuantity  );
    }

    //This method increase the stock quantity
    public void increase_quantity(int quantity){
        stockQuantity += quantity;
    }

    //This method decreses the stock quantity
    public void decrease_quantity(int quantity){
        if (stockQuantity > quantity){
            stockQuantity -= quantity;
        }
        else{
            System.out.println("Not enough stock");
        }
    }

    //Getter methods for encapsulation
    public String getName(){
        return name;
    }

    public double getSellingPrice(){
        return sellingPrice;
    }

    public int getStockQuantity(){
        return stockQuantity;
    }

    public double getPurchasePrice(){
        return purchasePrice;
    }

    public double getMrp(){
        return mrp;
    }

    public double getTaxPercentage(){
        return taxPercentage;
    }

    public double getDiscountPercentage(){
        return discountPercentage;
    }
}
