package Inventory_management.product;

public class ProductTallying {
    //This method increase the stock quantity
    public void increase_quantity(Product product,int quantity){
        int stockQuantity = product.getStockQuantity();
        stockQuantity += quantity;
        product.setStockQuantity(stockQuantity);
    }

    //This method decreses the stock quantity
    public void decrease_quantity(Product product,int quantity){
        if (product.getStockQuantity() > quantity){
            int product_quantity = product.getStockQuantity();
            product_quantity-= quantity;
            product.setStockQuantity(product_quantity);
        }
        else{
            System.out.println("Not enough stock");
        }
    }
}
