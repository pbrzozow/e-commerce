package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderItem {
    private String productId;
    long quantity;

    OrderItemDto dto(){
        return OrderItemDto.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }
}
