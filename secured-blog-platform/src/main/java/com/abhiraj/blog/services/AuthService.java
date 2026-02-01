package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.requests.LoginRequestDto;
import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.JwtResponseDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    UserResponseDto addNewUser(UserRequestDto userRequestDto);

    @Nullable JwtResponseDto verify(LoginRequestDto loginRequestDto);
}
