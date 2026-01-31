package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.mappings.UserMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public UserResponseDto addNewUser(UserRequestDto userRequestDto) {
        User user = userMapping.requestToUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        Long commentCount = commentRepository.countByCommenterId(savedUser.getId());
        Long postCount = postRepository.countByAuthorId(savedUser.getId());
        return userMapping.userToResponse(savedUser, commentCount, postCount);
    }
}
