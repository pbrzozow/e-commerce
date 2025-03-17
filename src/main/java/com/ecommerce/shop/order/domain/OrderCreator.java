package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.order.dto.AddressDto;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.CustomerDto;

import java.time.LocalDateTime;

class OrderCreator {

    Order from(CreateOrderRequest request, CartDto cartDto){
        return Order.builder()
                .cartDto(cartDto)
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customerInfo(mapToInfo(request.customerDto()))
                .build();
    }

    private CustomerInfo mapToInfo(CustomerDto customerDto){
        return CustomerInfo.builder()
                .firstName(customerDto.firstName())
                .lastName(customerDto.lastName())
                .email(customerDto.email())
                .address(mapAddress(customerDto.address()))
                .build();
    }
    private Address mapAddress(AddressDto addressDto){
        return Address.builder()
                .street(addressDto.street())
                .postalCode(addressDto.postalCode())
                .country(addressDto.country())
                .build();
    }
}
