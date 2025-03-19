package com.ecommerce.shop.order.domain.cart;

import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CartConfiguration {

    CartService cartService(CurrentUserGetter currentUserGetter) {
        InMemoryCartRepository cartRepository = new InMemoryCartRepository();
        return cartService(currentUserGetter, cartRepository);
    }

    @Bean
    CartService cartService(CurrentUserGetter currentUserGetter, CartRepository cartRepository) {
        ItemCreator itemCreator = new ItemCreator();
        return new CartService(currentUserGetter, itemCreator, cartRepository);
    }
}
