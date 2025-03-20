package com.ecommerce.shop.shipment.domain;

import com.ecommerce.shop.shipment.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Address {
    private String street;
    private String postalCode;
    private String country;

    AddressDto dto() {
        return AddressDto.builder()
                .street(street)
                .postalCode(postalCode)
                .country(country)
                .build();
    }
}
