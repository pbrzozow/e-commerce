package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.dto.AddressDto;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.CustomerDto;

import java.time.LocalDateTime;

class OrderCreator {

    Order from(CreateOrderRequest request, Cart cart) {
        return Order.builder()
                .cart(cart)
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customerInfo(mapToInfo(request.customerDto()))
                .build();
    }


    private CustomerInfo mapToInfo(CustomerDto customerDto) {
        return CustomerInfo.builder()
                .firstName(customerDto.firstName())
                .lastName(customerDto.lastName())
                .email(customerDto.email())
                .orderAddress(mapAddress(customerDto.address()))
                .build();
    }

    static OrderAddress mapAddress(AddressDto addressDto) {
        return OrderAddress.builder()
                .street(addressDto.street())
                .postalCode(addressDto.postalCode())
                .country(addressDto.country())
                .build();
    }
}
