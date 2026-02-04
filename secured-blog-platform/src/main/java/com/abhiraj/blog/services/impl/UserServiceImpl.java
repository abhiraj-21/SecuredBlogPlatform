package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.UserNotFoundException;
import com.abhiraj.blog.mappings.UserMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.CurrentUserService;
import com.abhiraj.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final CurrentUserService currentUserService;
    private final UserMapping userMapping;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto getUser() {
        User principal = currentUserService.getAuthenticatedUser();
        User freshUser = userRepository.findById(principal.getId()).orElseThrow(() ->
                    new UserNotFoundException("No User with user id "+principal.getId())
                );
        Long commentCount = commentRepository.countByCommenterId(freshUser.getId());
        Long postCount = postRepository.countByAuthorId(freshUser.getId());
        return userMapping.userToResponse(freshUser, commentCount, postCount);
    }
}
