package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.ItemDto;

class ItemCreator {
    Item from(ItemDto itemDto) {
        return Item.builder()
                .productId(itemDto.productId())
                .productName(itemDto.productName())
                .image(itemDto.image())
                .price(itemDto.price())
                .quantity(itemDto.quantity())
                .build();

    }
}
