package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class CartItem {
    private String productId;
    private int quantity;

    CartItemDto dto(){
        return CartItemDto.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}
