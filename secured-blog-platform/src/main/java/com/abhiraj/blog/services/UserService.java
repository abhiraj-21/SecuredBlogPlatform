package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import org.jspecify.annotations.Nullable;

public interface UserService {
    UserResponseDto getUser();
}
