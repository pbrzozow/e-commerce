package com.ecommerce.shop.order.dto.exception;

public class PaymentFailureException extends RuntimeException {
    public PaymentFailureException(String id) {
        super("Payment for order with id: " + id + " was rejected.");
    }
}
