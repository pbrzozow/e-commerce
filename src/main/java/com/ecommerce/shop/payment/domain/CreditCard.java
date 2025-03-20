package com.ecommerce.shop.payment.domain;

import lombok.Builder;

@Builder
record CreditCard(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
}
