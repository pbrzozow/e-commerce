package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.ItemDto;
import com.ecommerce.shop.order.dto.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;

import static com.ecommerce.infrastructure.authentication.CurrentUserGetter.getSignedInUserEmail;

@RequiredArgsConstructor
public class CartService {

    private final ItemCreator itemCreator;
    private final CartRepository cartRepository;

    public Cart add(ItemDto itemDto) {
        validateQuantity(itemDto.quantity());
        Cart userCart = getCart();
        Item cartItem = getCartItem(itemDto, userCart);

        if (cartItem == null) {
            cartItem = itemCreator.from(itemDto);
            userCart.getItems().add(cartItem);
        }
        cartItem.setQuantity(itemDto.quantity());

        return cartRepository.save(userCart);
    }

    private Item getCartItem(ItemDto itemDto, Cart userCart) {
        return userCart
                .getItems()
                .stream()
                .filter(item -> item.getProductId().equals(itemDto.productId()))
                .findFirst()
                .orElse(null);
    }

    public Cart clearCart() {
        Cart cart = getCart();
        cart.setItems(new ArrayList<>());
        cart.setPrice(0);
        return cartRepository.save(cart);
    }

    public Cart update(String id, int quantity) {
        validateQuantity(quantity);
        Cart cart = getCart();

        Item item = cart.getItems()
                .stream()
                .filter(it -> it.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(id));

        if (quantity == 0) {
            cart.getItems().remove(item);
        } else {
            item.setQuantity(quantity);
        }
        return cartRepository.save(cart);
    }


    public Cart getCart() {
        String username = getSignedInUserEmail().orElseThrow(() -> new UsernameNotFoundException("Cannot find a username."));
        Cart cart = cartRepository.findByUsername(username);
        return cart != null ? cartWithPrice(cart) : initializeCart(username);
    }


    private static Cart initializeCart(String username) {
        return new Cart(username, new ArrayList<>(), 0);
    }

    private Cart cartWithPrice(Cart cart) {
        double price = getPrice(cart);
        cart.setPrice(price);
        return cart;
    }

    private double getPrice(Cart cart) {
        return cart.getItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Provided quantity was below zero.");
        }
    }
}
