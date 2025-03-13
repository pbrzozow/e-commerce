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
class CartManager {
    private final CartRepository cartRepository;
    private final CurrentUserGetter userGetter;

    CartItem add(ProductDto productDto, int quantity){
        requireNonNull(productDto);
        if (quantity<=0){
            throw new IllegalArgumentException("Provided quantity was below zero.");
        }
        CartItem cartItem = new CartItem(productDto.getId(), quantity);
        Cart userCart = getCart();
        userCart.getItems().add(cartItem);
        cartRepository.save(userCart);
        return cartItem;
    }

    CartItem update(ProductDto productDto, int quantity){
        CartItem cartItem = new CartItem(productDto.getId(), quantity);
        Cart cart = getCart();
        if (quantity==0) {
            cart.getItems().removeIf(t -> Objects.equals(t.getProductId(), productDto.getId()));
        }else if (quantity<0){
            throw new IllegalArgumentException("Provided quantity was below zero.");
        }else {
            cart.getItems().removeIf(t -> Objects.equals(t.getProductId(), productDto.getId()));
            cart.getItems().add(cartItem);
        }
        cartRepository.save(cart);
        return cartItem;
    }
    Cart getCart(){
        Optional<String> username = userGetter.getSignedInUsername();
        Cart cart = cartRepository.findByUsername(username.get());
        if (cart==null){
            cart = new Cart(username.get(),new ArrayList<>());
        }
        return cart;
    }
}
