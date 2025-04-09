package com.ecommerce.shop.product.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record ProductDto(
        String id,
        @NotNull
        String name,
        @PositiveOrZero
        long amount,
        @PositiveOrZero
        double price,
        @NotNull
        String description,
        @NotNull
        String image,
        @NotNull
        CategoryDto category
) {
}
