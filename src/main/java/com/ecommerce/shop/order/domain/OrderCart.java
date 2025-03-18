package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.order.dto.OrderCartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderCart {
    private String username;
    private List<OrderItem> items;
    private double price;
OrderCartDto dto(){
return OrderCartDto.builder()
        .username(username)
        .items(items.stream().map(item->item.dto()).toList())
        .build();
}
}
