package com.ecommerce.shop.order.dto;

import jakarta.annotation.Nonnull;

public record PaymentDetails(@Nonnull String cardNumber,@Nonnull String cardHolderName,@Nonnull String expirationDate,@Nonnull String cvv) {
}
