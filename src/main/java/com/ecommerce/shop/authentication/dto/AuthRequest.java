package com.ecommerce.shop.authentication.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(@Nonnull @NotEmpty String email, @Nonnull @NotEmpty String password) {
}
