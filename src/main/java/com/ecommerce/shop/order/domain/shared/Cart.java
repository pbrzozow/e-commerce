package com.ecommerce.shop.order.domain.shared;

import com.ecommerce.shop.order.dto.CartDto;
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
public class Cart {
    private String username;
    private List<Item> items;
    private double price;

    public CartDto dto() {
        return CartDto.builder()
                .username(username)
                .items(items.stream().map(Item::dto).toList())
                .price(price)
                .build();
    }
}
