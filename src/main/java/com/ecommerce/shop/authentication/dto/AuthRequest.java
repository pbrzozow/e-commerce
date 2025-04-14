package com.ecommerce.shop.authentication.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(@Email String email, @Nonnull @NotEmpty String password) {
}
