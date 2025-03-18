package com.ecommerce.shop.order.dto;

public class RefundFailureException extends RuntimeException {
    public RefundFailureException(String id) {
    super("Refund for order with id: " +id+" was unsuccessful.");
  }
}
