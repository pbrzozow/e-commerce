package com.ecommerce.shop.order.domain.spi.dto;

import com.ecommerce.shop.order.dto.PaymentDetails;

public record PaymentRequest(String orderId, double price, PaymentDetails paymentDetails) {
}
