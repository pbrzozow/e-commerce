package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.AddressDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Address {
    private String street;
    private String postalCode;
    private String country;

    AddressDto dto(){
        return AddressDto.builder()
                .street(street)
                .postalCode(postalCode)
                .country(country)
                .build();
    }
}
