package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CartConfiguration {

    CartService cartService() {
        InMemoryCartRepository cartRepository = new InMemoryCartRepository();
        return cartService(cartRepository);
    }

    @Bean
    CartService cartService(CartRepository cartRepository) {
        ItemCreator itemCreator = new ItemCreator();
        return new CartService(currentUserGetter, itemCreator, cartRepository);
    }
}
