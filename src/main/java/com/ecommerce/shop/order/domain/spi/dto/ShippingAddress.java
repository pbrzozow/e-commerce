package com.ecommerce.shop.order.domain.spi.dto;

import lombok.Builder;

@Builder
public record ShippingAddress(String street,String postalCode,String country) {
}
