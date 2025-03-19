package com.ecommerce.shop.order.domain.shared;

import com.ecommerce.shop.order.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String productId;
    private String productName;
    private String image;
    private double price;
    private int quantity;

    public ItemDto dto() {
        return ItemDto.builder()
                .productId(productId)
                .productName(productName)
                .image(image)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
