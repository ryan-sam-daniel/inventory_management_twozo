package com.twozo.bill;

import com.twozo.product.Product;
import java.util.List;

public class BillViewer {
    public void viewBill(List<Product> billItems) {
        billItems.forEach(product -> System.out.println(
                "Name: " + product.getName() +
                " | Quantity: " + product.getStockQuantity() +
                " | Price: " + product.getSellingPrice()
        ));
    }
}
