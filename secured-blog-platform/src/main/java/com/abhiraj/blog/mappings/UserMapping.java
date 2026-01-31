package com.abhiraj.blog.mappings;

import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapping {

    public User requestToUser(UserRequestDto userRequestDto){
        return User.builder()
                .password(userRequestDto.getPassword())
                .email(userRequestDto.getEmail())
                .username(userRequestDto.getUsername())
                .build();
    }

    public UserResponseDto userToResponse(User user, Long commentCount, Long postCount){
        return UserResponseDto.builder()
                .email(user.getEmail())
                .postCount(postCount)
                .username(user.getUsername())
                .commentCount(commentCount)
                .build();
    }

}
