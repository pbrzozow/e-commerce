package com.ecommerce.shop.order.dto;

import lombok.Builder;

@Builder
public record AddressDto(String street,String postalCode,String country) {
}
