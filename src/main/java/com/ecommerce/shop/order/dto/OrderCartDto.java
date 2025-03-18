package com.ecommerce.shop.order.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderCartDto(String username, List<OrderItemDto> items, double price) {
}
