package com.ecommerce.infrastructure.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class CurrentUserGetter {

    public Optional<String> getSignedInUsername(){
        String username = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context!=null) {
            Authentication authentication = context.getAuthentication();
            if (authentication!=null){
                username = authentication.getName();
            }
        }
        return Optional.ofNullable(username);
    }
}
