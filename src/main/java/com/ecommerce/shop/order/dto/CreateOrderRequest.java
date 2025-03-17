package com.ecommerce.shop.order.dto;

import jakarta.annotation.Nonnull;

public record CreateOrderRequest(@Nonnull CustomerDto customerDto, @Nonnull PaymentDetails paymentDetails) {}
