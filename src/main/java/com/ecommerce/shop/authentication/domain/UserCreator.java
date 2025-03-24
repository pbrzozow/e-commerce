package com.ecommerce.shop.authentication.domain;

import com.ecommerce.shop.authentication.dto.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
class UserCreator {
    private final PasswordEncoder passwordEncoder;

    User toEntity(CreateUserDto userDto) {
        return User.builder()
                .email(userDto.email())
                .password(passwordEncoder.encode(userDto.password()))
                .role(Role.USER)
                .build();
    }
}
