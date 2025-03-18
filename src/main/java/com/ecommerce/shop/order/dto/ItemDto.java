package com.ecommerce.shop.order.dto;

import lombok.Builder;

@Builder
public record ItemDto(String productId, String productName, String image, double price, int quantity) {
}
