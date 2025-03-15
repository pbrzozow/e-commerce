package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.costcalculator.CostCalculatorFacade;
import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.ecommerce.shop.product.domain.ProductFacade;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.Objects;


@RequiredArgsConstructor
class CartManager {
    private final CartRepository cartRepository;
    private final CurrentUserGetter userGetter;
    private final CostCalculatorFacade calculatorFacade;


    CartItem add(String id, int quantity){
        Cart userCart = getCart();
        CartItem cartItem = getCartItem(id, userCart);
        cartItem.increaseQuantity(quantity);
        cartRepository.save(userCart);
        return cartItem;
    }

    private CartItem getCartItem(String id, Cart userCart) {
        return userCart
                 .getItems()
                 .stream()
                 .filter(c -> c.getProductId().equals(id))
                 .findFirst().orElseGet(()-> createNewCartItem(userCart,id));
    }

    private CartItem createNewCartItem(Cart userCart,String id) {
        CartItem newItem = new CartItem(id, 0);
        userCart.getItems().add(newItem);
        return newItem;
    }

    Cart clearCart(){
        Cart cart = getCart();
        cart.setItems(new ArrayList<>());
        return cartRepository.save(cart);
    }

    CartItem update(String id, int quantity){
        CartItem cartItem = new CartItem(id, quantity);
        Cart cart = getCart();

        cart.getItems().removeIf(item -> Objects.equals(item.getProductId(), id));
        if (quantity>0) {
            cart.getItems().add(cartItem);
        }
        cartRepository.save(cart);
        return cartItem;
    }

    Cart getCart(){
        String username = userGetter.getSignedInUsername().orElseThrow();
        Cart cart = cartRepository.findByUsername(username);
        return cart!=null ? cartWithPrice(cart): initializeCart(username);
    }

    private static Cart initializeCart(String username) {
        return new Cart(username,new ArrayList<>(),0);
    }

    private Cart cartWithPrice(Cart cart) {
        double price = getPrice(cart);
        cart.setPrice(price);
        return cart;
    }

    private double getPrice(Cart cart) {
        return calculatorFacade
                .calculate(cart.getItems().stream().map(CartItem::dto).toList());
    }
}
