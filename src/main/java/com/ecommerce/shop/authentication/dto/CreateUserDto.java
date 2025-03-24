package com.ecommerce.shop.authentication.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserDto(@Nonnull @NotEmpty String email, @Nonnull @NotEmpty String password) {
}
