package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.CategoryDto;

enum Category {
    LEATHER;

    CategoryDto dto(){
        return CategoryDto.valueOf(name());
    }
}
