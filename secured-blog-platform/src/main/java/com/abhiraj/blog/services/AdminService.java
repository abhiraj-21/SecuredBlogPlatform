package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Page<UserResponseDto> getAllRegisteredUsers(Pageable pageable);
}
