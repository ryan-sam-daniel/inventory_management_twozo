package com.twozo.app.validator;

import com.twozo.app.model.Product;
import com.twozo.app.model.ProductSearchDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
    public StringBuilder validateAdding(final Product product){
        final StringBuilder validationErrors = new StringBuilder();

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            validationErrors.append("Product name is required. ");
        }
        if (product.getPurchasePrice() <= 0) {
            validationErrors.append("Purchase price must be greater than 0. ");
        }
        if (product.getSellingPrice() <= 0) {
            validationErrors.append("Selling price must be greater than 0. ");
        }
        if (product.getMrp() < product.getSellingPrice()) {
            validationErrors.append("MRP should not be less than selling price. ");
        }
        if (product.getTaxPercentage() < 0) {
            validationErrors.append("Tax percentage should not be negative . ");
        }
        if (product.getDiscountPercentage() < 0 || product.getDiscountPercentage() > 100) {
            validationErrors.append("Discount percentage must be between 0 and 100. ");
        }
        if (product.getStockQuantity() <= 0) {
            validationErrors.append("Stock quantity must be greater than 0. ");
        }

        return validationErrors;
    }


}
