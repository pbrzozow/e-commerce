package com.ecommerce.shop.order.domain.spi.dto;

public record RefundRequest(String orderId,double amount) {
}
