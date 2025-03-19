package com.ecommerce.shop.payment.dto;


public record PaymentRequest(String orderId, double price, CreditCardDto creditCardDto) {
}
