package com.ecommerce.shop.order.dto.exception;

public class UserNotLoggedInException extends RuntimeException {
    public UserNotLoggedInException() {
        super("User is not authenticated.");
    }
}
