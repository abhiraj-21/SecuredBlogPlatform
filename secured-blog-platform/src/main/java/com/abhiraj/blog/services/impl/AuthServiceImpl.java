package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.requests.LoginRequestDto;
import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.JwtResponseDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.UserNotFoundException;
import com.abhiraj.blog.jwt.service.JwtService;
import com.abhiraj.blog.mappings.UserMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserMapping userMapping;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Override
    public UserResponseDto addNewUser(UserRequestDto userRequestDto) {
        User user = userMapping.requestToUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        Long commentCount = commentRepository.countByCommenterId(savedUser.getId());
        Long postCount = postRepository.countByAuthorId(savedUser.getId());
        return userMapping.userToResponse(savedUser, commentCount, postCount);
    }

    @Override
    public @Nullable JwtResponseDto verify(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() ->
                new UserNotFoundException("No user with email "+loginRequestDto.getEmail())
        );
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getEmail(), loginRequestDto.getPassword())  //if we pass user.getPassword() then we would be passing the encoded password
        );
        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Invalid email or password");
        }
        return jwtService.generateToken(user);
    }
}
