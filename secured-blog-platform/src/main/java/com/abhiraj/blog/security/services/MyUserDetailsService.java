package com.abhiraj.blog.security.services;

import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.UserNotFoundException;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.security.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()->
                    new UserNotFoundException("No user with email: "+email)
                );

        return new UserPrincipal(user);
    }
}
