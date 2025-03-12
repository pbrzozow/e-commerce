package com.shop.ecommerce.infrastructure.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

class CurrentUserGetter {

    private String getSignedInUsername(){
        String username = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context!=null) {
            Authentication authentication = context.getAuthentication();
            if (authentication!=null){
                username = authentication.getName();
            }
        }
        return username;
    }

    public String getSignedInUsernameOrAnonymous(){
        String username = getSignedInUsername();
        return username!=null ? username : "anonymous";
    }
}
