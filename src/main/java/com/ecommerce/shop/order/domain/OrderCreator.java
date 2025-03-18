package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.cart.dto.CartItemDto;
import com.ecommerce.shop.order.dto.AddressDto;
import com.ecommerce.shop.order.dto.CreateOrderRequest;
import com.ecommerce.shop.order.dto.CustomerDto;

import java.time.LocalDateTime;

class OrderCreator {

    Order from(CreateOrderRequest request, CartDto cartDto){
        return Order.builder()
                .orderCart(mapToCart(cartDto))
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customerInfo(mapToInfo(request.customerDto()))
                .build();
    }
    private OrderCart mapToCart(CartDto cartDto){
        return OrderCart.builder()
                .username(cartDto.getUsername())
                .items(cartDto.getItems().stream().map(this::mapToItem).toList())
                .build();
    }
    private OrderItem mapToItem(CartItemDto cartItemDto){
        return OrderItem.builder()
                .productId(cartItemDto.getProductId())
                .quantity(cartItemDto.getQuantity())
                .build();
    }
    private CustomerInfo mapToInfo(CustomerDto customerDto){
        return CustomerInfo.builder()
                .firstName(customerDto.firstName())
                .lastName(customerDto.lastName())
                .email(customerDto.email())
                .orderAddress(mapAddress(customerDto.address()))
                .build();
    }

    static OrderAddress mapAddress(AddressDto addressDto){
        return OrderAddress.builder()
                .street(addressDto.street())
                .postalCode(addressDto.postalCode())
                .country(addressDto.country())
                .build();
    }
}
