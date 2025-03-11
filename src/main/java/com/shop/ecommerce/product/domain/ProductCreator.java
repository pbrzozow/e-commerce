package com.shop.ecommerce.product.domain;

import com.shop.ecommerce.product.dto.ProductDto;

class ProductCreator {
    Product from(ProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .build();
    }
}
