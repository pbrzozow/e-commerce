package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;

interface CartRepository {
    Cart findByUsername(String username);

    Cart save(Cart cart);
}
