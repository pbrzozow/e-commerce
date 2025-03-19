package com.ecommerce.shop.payment.dto;

import lombok.Builder;

@Builder
public record PaymentDto(String orderId, PaymentStatusDto paymentStatusDto) {
}
