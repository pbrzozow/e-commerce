package com.ecommerce.shop.order.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CartDto(String username, List<ItemDto> items, double price) {
}
