package com.ecommerce.shop.cart.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private String productId;
    private long quantity;
}
