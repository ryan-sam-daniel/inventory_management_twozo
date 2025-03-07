public class Product{
    public String name;
    public double purchasePrice;
    public double mrp;
    public double taxPercentage;
    public double sellingPrice;
    public double discountPercentage;
    public int stockQuantity;

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
    public double totalprice(){
        double sp = this.stockQuantity*this.sellingPrice;
        sp = sp + (sp*this.taxPercentage)/100;
        return sp;
    }
    public void displayProduct() {
        System.out.println(name + " | Stock: " + stockQuantity + " | Price: " + sellingPrice);
    }
    public String getName(){
        return name;
    }
    public void reduceStock(int quantity){
        this.stockQuantity-=quantity;
    }
    public void increaseStock(int quantity){
        this.stockQuantity+=quantity;
    }
}