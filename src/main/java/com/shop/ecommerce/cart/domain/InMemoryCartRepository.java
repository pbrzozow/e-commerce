package com.shop.ecommerce.cart.domain;

import java.util.concurrent.ConcurrentHashMap;

class InMemoryCartRepository implements CartRepository {
    private final ConcurrentHashMap<String,Cart> carts = new ConcurrentHashMap<>();
    @Override
    public Cart save(Cart cart) {
        carts.put(cart.getUsername(),cart);
        return cart;
    }

    @Override
    public Cart findByUsername(String username) {
        return carts.get(username);
    }
}
