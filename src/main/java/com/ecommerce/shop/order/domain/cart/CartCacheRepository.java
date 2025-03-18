package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.shop.order.domain.shared.Cart;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
class CartCacheRepository implements CartRepository {
    private static final String CART_PREFIX = "cart:";
    private final RedisTemplate<String, Cart> redisTemplate;

    public CartCacheRepository(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Cart save(Cart cart) {
        redisTemplate.opsForValue().set(CART_PREFIX + cart.getUsername(), cart, Duration.ofHours(1));
        return cart;
    }

    @Override
    public Cart findByUsername(String username) {
        return redisTemplate.opsForValue().get(CART_PREFIX + username);
    }

}
