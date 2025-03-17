package com.ecommerce.shop.order.domain.spi.dto;

public record PaymentRequest(String orderId,double price) {
}
