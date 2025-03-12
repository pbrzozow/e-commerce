package com.shop.ecommerce.cart.domain;

import com.shop.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CartConfiguration {

    @Autowired
    private CurrentUserGetter currentUserGetter;

    CartFacade cartFacade(){
        return cartFacade(new InMemoryCartRepository());
    }

    @Bean
    CartFacade cartFacade(CartRepository cartRepository){
        return new CartFacade(cartRepository,currentUserGetter);
    }
}
