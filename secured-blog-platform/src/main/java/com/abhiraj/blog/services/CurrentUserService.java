package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.security.domain.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthenticated access");
        }

        if (!(authentication.getPrincipal() instanceof UserPrincipal principal)) {
            throw new RuntimeException("Invalid authentication principal");
        }

        return principal.getUser();
    }
}