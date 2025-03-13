package com.shop.ecommerce.cart.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private String productId;
    private int quantity;
}
