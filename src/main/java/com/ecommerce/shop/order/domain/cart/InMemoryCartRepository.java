package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryCartRepository implements CartRepository {
    private final ConcurrentHashMap<String, Cart> carts = new ConcurrentHashMap<>();

    @Override
    public Cart findByUsername(String username) {
        return carts.get(username);
    }

    @Override
    public Cart save(Cart cart) {
        carts.put(cart.getUsername(), cart);
        return cart;
    }
}
