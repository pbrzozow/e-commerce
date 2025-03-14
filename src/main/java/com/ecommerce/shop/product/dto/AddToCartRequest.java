package com.ecommerce.shop.product.dto;

import jakarta.annotation.Nonnull;

public record AddToCartRequest(@Nonnull String id, int quantity) {
}
