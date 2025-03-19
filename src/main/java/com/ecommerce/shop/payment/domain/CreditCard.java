package com.ecommerce.shop.payment.domain;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
}
