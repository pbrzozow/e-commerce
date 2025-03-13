package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.cart.dto.CartDto;
import com.shop.ecommerce.cart.dto.CartItemDto;
import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.shop.ecommerce.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;



import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class CartFacade {
    private final CartManager cartManager;


    public CartItemDto add(String id,int quantity){
        requireNonNull(id);
        validateQuantity(quantity);
        CartItem item = cartManager.add(id, quantity);
        return item.dto();
    }

    public CartDto getCart(){
        return cartManager.getCart().dto();
    }

    public CartItemDto update(String id, int quantity){
        requireNonNull(id);
        validateQuantity(quantity);
        CartItem item = cartManager.update(id, quantity);
        return item.dto();
    }

    private static void validateQuantity(int quantity) {
        if (quantity <0){
            throw new IllegalArgumentException("Provided quantity was below zero.");
        }
    }

}
