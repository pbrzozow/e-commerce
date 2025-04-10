package com.ecommerce.shop.authentication.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findUserByRole(Role.ADMIN).isEmpty()) {
            User admin = User.builder()
                    .email("a@a")
                    .password(passwordEncoder.encode("aaa"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }
}