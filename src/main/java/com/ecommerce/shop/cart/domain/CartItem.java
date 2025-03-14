package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.cart.dto.CartItemDto;
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
    int increaseQuantity(int num){
        return quantity+=num;
    }
}
