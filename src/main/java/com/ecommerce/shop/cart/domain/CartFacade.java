package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.cart.dto.CartDto;
import com.ecommerce.shop.cart.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import static java.util.Objects.requireNonNull;

@Transactional
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
        Cart cart = cartManager.getCart();
        return cart.dto();
    }

    public CartDto clearCart(){
        Cart cart = cartManager.clearCart();
        return cart.dto();
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
