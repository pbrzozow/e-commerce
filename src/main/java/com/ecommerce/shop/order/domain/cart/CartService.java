package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import com.ecommerce.shop.order.domain.shared.Cart;
import com.ecommerce.shop.order.domain.shared.Item;
import com.ecommerce.shop.order.dto.ItemDto;
import com.ecommerce.shop.order.dto.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;


@RequiredArgsConstructor
public class CartService {
    private final CurrentUserGetter userGetter;
    private final ItemCreator itemCreator;
    private final CartRepository cartRepository;

    public Item add(ItemDto itemDto) {
        Cart userCart = getCart();
        Item cartItem = getCartItem(itemDto, userCart);

        if (cartItem == null) {
            cartItem = itemCreator.from(itemDto);
            userCart.getItems().add(cartItem);
        }
        cartItem.setQuantity(cartItem.getQuantity() + itemDto.quantity());

        cartRepository.save(userCart);
        return cartItem;
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
        return cartRepository.save(cart);
    }

    public Item update(String id, int quantity) {
        Cart cart = getCart();

        Item item = cart.getItems()
                .stream()
                .filter(it -> it.getProductId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException(id));

        item.setQuantity(quantity);
        cartRepository.save(cart);
        return item;
    }


    public Cart getCart() {
        String username = userGetter.getSignedInUsername().orElseThrow();
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
