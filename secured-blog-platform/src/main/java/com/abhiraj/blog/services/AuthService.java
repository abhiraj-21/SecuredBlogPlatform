package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;

public interface AuthService {
    UserResponseDto addNewUser(UserRequestDto userRequestDto);
}
