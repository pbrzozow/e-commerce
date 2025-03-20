package com.ecommerce.infrastructure.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CurrentUserGetter {

    public static Optional<String> getSignedInUserEmail() {
        String email = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                email = authentication.getName();
            }
        }
        return Optional.ofNullable(email);
    }
}
