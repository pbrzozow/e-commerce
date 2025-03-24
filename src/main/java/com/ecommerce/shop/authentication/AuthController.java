package com.ecommerce.shop.authentication;

import com.ecommerce.shop.authentication.domain.AuthenticationFacade;
import com.ecommerce.shop.authentication.dto.AuthRequest;
import com.ecommerce.shop.authentication.dto.CreateUserDto;
import com.ecommerce.shop.authentication.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
class AuthController {
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {
        Map<String, String> tokens = authenticationFacade.authenticate(authRequest.email(), authRequest.password());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody @Valid CreateUserDto userDto) {
        UserDto user = authenticationFacade.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/refresh")
    ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        Map<String, String> token = authenticationFacade.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Refresh token is required");
        }
        authenticationFacade.logout(refreshToken);
        return ResponseEntity.ok("Successfully logged out");
    }
}
