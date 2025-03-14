package com.ecommerce.shop.product.dto;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super("Product with id: "+id+" was not found.");
    }
}
