package com.shop.ecommerce.cart.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {
    private String username;
    private List<CartItemDto> items;
    private double price;

}
