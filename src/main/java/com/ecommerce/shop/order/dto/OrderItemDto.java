package com.ecommerce.shop.order.dto;

import lombok.Builder;

@Builder
public record OrderItemDto(String productId,long quantity) {
}
