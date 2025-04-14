package com.ecommerce.shop.order.dto.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super("Order with id: "+ id + " was not found.");
    }
}
