package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CartFacade {
    private final CartManager cartManager;


    CartItemDto add(ProductDto productDto,int quantity){
        requireNonNull(productDto);
        CartItem item = cartManager.add(productDto, quantity);
        return item.dto();
    }

    CartDto getCart(){
        return cartManager.getCart().dto();
    }

    CartItemDto update(ProductDto productDto, int quantity){
        requireNonNull(productDto);
        CartItem item = cartManager.update(productDto, quantity);
        return item.dto();
    }

}
