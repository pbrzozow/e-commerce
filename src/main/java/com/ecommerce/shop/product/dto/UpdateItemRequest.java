package com.ecommerce.shop.product.dto;

import jakarta.annotation.Nonnull;

public record UpdateItemRequest(@Nonnull String id, int quantity) {
}
