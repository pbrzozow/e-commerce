package com.ecommerce.shop.order.dto;

import lombok.Builder;

@Builder
public record CustomerDto(String firstName,
         String lastName, String email,AddressDto address) {
}
