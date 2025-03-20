package com.twozo.purchase;

import java.time.LocalDateTime;

import com.twozo.product.Product;

public class Purchase {
    private Product product;
    private LocalDateTime purchaseDateTime;

    public Purchase(Product product, LocalDateTime purchaseDateTime) {
        this.product = product;
        this.purchaseDateTime = purchaseDateTime;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }
}

