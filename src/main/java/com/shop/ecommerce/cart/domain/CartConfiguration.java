package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CartConfiguration {


    CartFacade cartFacade(CurrentUserGetter currentUserGetter){
        return cartFacade(new InMemoryCartRepository(),currentUserGetter);
    }

    @Bean
    CartFacade cartFacade(CartRepository cartRepository,CurrentUserGetter currentUserGetter){
        CartManager cartManager = new CartManager(cartRepository, currentUserGetter);
        return new CartFacade(cartManager);
    }
}
