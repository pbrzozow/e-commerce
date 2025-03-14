package com.ecommerce.shop.order.domain;

import com.ecommerce.shop.cart.domain.CartFacade;
import com.ecommerce.shop.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CartFetcher {
    private final CartFacade cartFacade;

    CartDto getCart(){
        return cartFacade.getCart();
    }
}
