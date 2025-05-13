package com.twozo.app.service;

import com.twozo.app.model.Product;
import com.twozo.app.model.ProductSearchDTO;
import com.twozo.app.model.PurchaseItem;
import com.twozo.app.model.SaleItem;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ProductServiceManager {
    
    Collection<Product> getAllProducts() ;
    Product getProduct(int id);
    void add(Product product) ;
    boolean remove(ProductSearchDTO productSearchDTO);
    boolean updateStock(Collection<PurchaseItem> cart);
    boolean removeStock(Collection<SaleItem> cart) ;
    double summateProductSubTotal(Product product, String mode);
    double summateProductTaxAmount(double subTotal, Product product);
    double summateProductFinalAmount(double amount, Product product);
    
}
