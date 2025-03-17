package com.twozo.inventory;

import com.twozo.product.Product;

public interface IProductFinder {
    Product getProduct(String name);
}
