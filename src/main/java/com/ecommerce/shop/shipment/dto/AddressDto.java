package com.ecommerce.shop.shipment.dto;

import lombok.Builder;

@Builder
public record AddressDto(String street, String postalCode, String country) {
}
