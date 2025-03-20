package com.twozo.inventory;

import com.twozo.product.Product;

public interface ProductFinderService {

    /**
     * @param name Given the name of the product as input
     * @return Product returns the product object from the inventory list
     */
    Product getProduct(String name);
}
