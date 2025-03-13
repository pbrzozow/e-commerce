package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
class CartManager {
    private final CartRepository cartRepository;
    private final CurrentUserGetter userGetter;

    CartItem add(String id, int quantity){
        Cart userCart = getCart();
        Optional<CartItem> existingItem = userCart
                .getItems()
                .stream()
                .filter(c -> c.getProductId().equals(id))
                .findFirst();
        if (existingItem.isPresent()){
            int exisitingQuantity = existingItem.get().getQuantity();
            quantity +=exisitingQuantity;
            userCart.getItems().remove(existingItem.get());
        }
        CartItem cartItem = new CartItem(id, quantity);
        userCart.getItems().add(cartItem);
        cartRepository.save(userCart);
        return cartItem;
    }

    CartItem update(String id, int quantity){
        CartItem cartItem = new CartItem(id, quantity);
        Cart cart = getCart();

        cart.getItems().removeIf(t -> Objects.equals(t.getProductId(), id));
        if (quantity>0) {
            cart.getItems().add(cartItem);
        }
        cartRepository.save(cart);
        return cartItem;
    }

    Cart getCart(){
        String username = userGetter.getSignedInUsername().orElseThrow();
        Cart cart = cartRepository.findByUsername(username);
        if (cart==null){
            cart = new Cart(username,new ArrayList<>());
        }
        return cart;
    }
}
