package com.ecommerce.shop.payment.dto;

import jakarta.annotation.Nonnull;

public record CreditCardDto(@Nonnull String cardNumber, @Nonnull String cardHolderName, @Nonnull String expirationDate,
                            @Nonnull String cvv) {
}
