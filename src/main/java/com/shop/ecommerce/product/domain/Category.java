package com.shop.ecommerce.product.domain;

import com.shop.ecommerce.product.dto.CategoryDto;

enum Category {
    LEATHER;

    CategoryDto dto(){
        return CategoryDto.valueOf(name());
    }
}
