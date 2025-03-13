package com.shop.ecommerce.product.dto;

import jakarta.annotation.Nonnull;

public record UpdateItemRequest(@Nonnull String id, int quantity) {
}
