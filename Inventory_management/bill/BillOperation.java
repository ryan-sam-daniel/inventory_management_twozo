package Inventory_management.bill;

import Inventory_management.product.Product;

public interface BillOperation {
    void addToBill(Product product, int quantity);
}
