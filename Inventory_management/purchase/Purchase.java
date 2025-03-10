package Inventory_management.purchase;

import java.util.ArrayList;
import Inventory_management.inventory.*;
import Inventory_management.product.*;
import Inventory_management.finance.*;

interface PurchaseOperation{
    public void addToList(String name, int quantity);
    public void viewPurchases();
}


public class Purchase implements PurchaseOperation{
    private ArrayList<Product> purchase_list;
    private Inventory inventory;

    //Constructor for Purchase Class
    public Purchase(Inventory inventory){
        this.inventory=inventory;
        this.purchase_list= new ArrayList<>();
    }

    //this method finds the product from inventory and adds it to a product list 
    @Override
    public void addToList(String name, int quantity){
        Product product = inventory.findProduct(name);
        if(product == null){
            System.out.println("Not found in the inventory");
        }
        else{
            purchase_list.add(new Product(product.getName(), product.getPurchasePrice(), product.getMrp(), product.getTaxPercentage(), product.getSellingPrice(), product.getDiscountPercentage(), quantity));
            ProductTallying producttallying = new ProductTallying();
            producttallying.increase_quantity(product,quantity);
            double expense = FinanceCalculation.calculate_expenses(product.getPurchasePrice(),quantity);
            FinanceValueAddition.addExpense(expense);
        }
    }

    //this is used to view the purchased products
    @Override
    public void viewPurchases(){
        for (Product p : purchase_list){
            System.out.println("Name : "+p.getName()+" Quantity : "+p.getStockQuantity());
        }
    }

}
