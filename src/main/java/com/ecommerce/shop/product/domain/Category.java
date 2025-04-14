package com.ecommerce.shop.product.domain;

import com.ecommerce.shop.product.dto.CategoryDto;

enum Category {
    SHOES,WALLET,BELT,BAG;

    CategoryDto dto(){
        return CategoryDto.valueOf(name());
    }
}
