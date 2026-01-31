package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path="/register")
    public ResponseEntity<UserResponseDto> registerNewUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(authService.addNewUser(userRequestDto));
    }

}
