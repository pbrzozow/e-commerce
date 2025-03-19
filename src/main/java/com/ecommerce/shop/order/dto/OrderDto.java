package com.ecommerce.shop.order.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderDto(String id, CustomerDto customerDto, com.ecommerce.shop.order.dto.CartDto cartDto,
                       LocalDateTime createdAt,
                       OrderStatusDto status) {
}
