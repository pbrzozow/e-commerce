package com.shop.ecommerce.cart.dto;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException() {
        super("User is not authenticated.");
    }
}
