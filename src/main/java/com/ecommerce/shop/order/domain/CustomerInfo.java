package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CustomerInfo {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

    CustomerDto dto(){
        return CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .address(address.dto())
                .build();
    }
}
