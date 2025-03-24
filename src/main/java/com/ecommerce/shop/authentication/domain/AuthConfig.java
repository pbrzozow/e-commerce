package com.ecommerce.shop.authentication.domain;

import com.ecommerce.infrastructure.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class AuthConfig {

    @Bean
    AuthenticationFacade authenticationFacade(JwtUtil jwtUtil, AuthenticationManager authManager,
                                              PasswordEncoder passwordEncoder, UserRepository userRepository) {
        UserCreator userCreator = new UserCreator(passwordEncoder);
        return new AuthenticationFacade(jwtUtil, authManager, userCreator, userRepository);
    }
}
