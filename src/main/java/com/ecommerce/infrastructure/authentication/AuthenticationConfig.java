package com.ecommerce.infrastructure.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AuthenticationConfig {

    @Bean
    CurrentUserGetter currentUserGetter(){
        return new CurrentUserGetter();
    }
}
