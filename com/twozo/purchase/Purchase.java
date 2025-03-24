package com.twozo.purchase;

import java.time.LocalDateTime;

import com.twozo.product.Product;

public class Purchase {
    private final Product product;
    private final LocalDateTime purchaseDateTime;

    public Purchase(final Product product, final LocalDateTime purchaseDateTime) {
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

