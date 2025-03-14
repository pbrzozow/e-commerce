package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "cart")
class Cart {
    private String username;
    private List<CartItem> items;
    private double price;

    CartDto dto(){
        return CartDto.builder()
                .username(username)
                .items(items.stream().map(CartItem::dto).toList())
                .price(price)
                .build();
    }
}
