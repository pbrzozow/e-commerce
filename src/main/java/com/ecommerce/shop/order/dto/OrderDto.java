package com.ecommerce.shop.order.dto;

import com.ecommerce.shop.cart.dto.CartDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderDto(String id, CustomerDto customerDto, OrderCartDto orderCartDto, LocalDateTime createdAt, OrderStatusDto status)
{ }
