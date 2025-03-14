package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.ProductDto;

class ProductCreator {
    Product from(ProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .availableAmount(dto.getAvailableAmount())
                .description(dto.getDescription())
                .image(dto.getImage())
                .category(Category.valueOf(dto.getCategory().name()))
                .build();
    }
}
