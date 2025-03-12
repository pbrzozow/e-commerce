package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartFacade {
    private final CurrentUserGetter userGetter;
    ProductDto add(ProductDto productDto){

    }
}
