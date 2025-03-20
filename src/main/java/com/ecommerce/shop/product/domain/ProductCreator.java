package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.ProductDto;

class ProductCreator {
    Product from(ProductDto dto) {
        return Product.builder()
                .id(dto.id())
                .name(dto.name())
                .price(dto.price())
                .description(dto.description())
                .image(dto.image())
                .category(Category.valueOf(dto.category().name()))
                .build();
    }
}
