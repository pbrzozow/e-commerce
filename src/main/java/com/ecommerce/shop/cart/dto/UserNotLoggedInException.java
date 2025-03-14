package com.ecommerce.shop.cart.dto;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException() {
        super("User is not authenticated.");
    }
}
