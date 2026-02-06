package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.mappings.UserMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Page<UserResponseDto> getAllRegisteredUsers(Pageable pageable) {
        Page<Object[]> rows = userRepository.findAllUsersWithCount(pageable);

        return rows.map(row -> {
            User user = (User) row[0];
            Long postCount = (Long) row[1];
            Long commentCount = (Long) row[2];
            return userMapping.userToResponse(user, commentCount, postCount);
        });
    }
}
