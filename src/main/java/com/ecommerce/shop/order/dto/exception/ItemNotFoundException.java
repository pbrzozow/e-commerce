package com.ecommerce.shop.order.dto.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String id) {
        super("Item with id: " + id + " was not found.");
    }
}
