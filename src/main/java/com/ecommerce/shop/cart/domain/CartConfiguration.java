package com.ecommerce.shop.cart.domain;

import com.ecommerce.shop.costcalculator.CostCalculatorFacade;
import com.ecommerce.infrastructure.authentication.CurrentUserGetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CartConfiguration {

    CartFacade cartFacade(CurrentUserGetter currentUserGetter,CostCalculatorFacade calculatorFacade){
        return cartFacade(new InMemoryCartRepository(),currentUserGetter,calculatorFacade);
    }

    @Bean
    CartFacade cartFacade(CartRepository cartRepository, CurrentUserGetter currentUserGetter, CostCalculatorFacade costCalculatorFacade){
        CartManager cartManager = new CartManager(cartRepository, currentUserGetter,costCalculatorFacade);
        return new CartFacade(cartManager);
    }
}
