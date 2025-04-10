package com.ecommerce.shop.authentication.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (userRepository.findUserByRole(Role.ADMIN).isEmpty()) {
            User admin = User.builder()
                    .email("a@a")
                    .password("aaa")
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }
}