package com.twozo.inventory;

import com.twozo.product.Product;

public interface IProductAdjuster {
    void increase_quantity ( Product product, int quantity );
    void decrease_quantity ( Product product, int quantity );
}
