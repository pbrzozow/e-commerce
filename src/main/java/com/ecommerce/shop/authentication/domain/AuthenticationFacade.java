package com.ecommerce.shop.authentication.domain;

import com.ecommerce.infrastructure.security.JwtUtil;
import com.ecommerce.shop.authentication.dto.CreateUserDto;
import com.ecommerce.shop.authentication.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@RequiredArgsConstructor
public class AuthenticationFacade {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserCreator userCreator;
    private final UserRepository userRepository;


    public Map<String, String> authenticate(String email, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            String accessToken = jwtUtil.generateAccessToken(email);
            String refreshToken = jwtUtil.generateRefreshToken(email);
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }
        return Map.of();
    }


    public UserDto register(CreateUserDto createUserDto) {
        User user = userCreator.toEntity(createUserDto);
        userRepository.save(user);
        return user.dto();
    }

    public void logout(String refreshToken) {
        jwtUtil.blacklistToken(refreshToken);
    }
}
