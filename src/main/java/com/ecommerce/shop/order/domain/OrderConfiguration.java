package com.ecommerce.shop.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderConfiguration {

    @Bean
    OrderFacade orderFacade(){
        return new OrderFacade();
    }
}
