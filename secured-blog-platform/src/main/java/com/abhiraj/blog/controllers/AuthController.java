package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.LoginRequestDto;
import com.abhiraj.blog.domain.dtos.requests.UserRequestDto;
import com.abhiraj.blog.domain.dtos.responses.JwtResponseDto;
import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Authentication",
        description = "Endpoints for user registration and authentication"
)

public class AuthController {

    private final AuthService authService;

    @PostMapping(path="/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponseDto> registerNewUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(authService.addNewUser(userRequestDto));
    }

    @PostMapping(path = "/login")
    @Operation(summary = "Authenticate a user and return a JWT")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.verify(loginRequestDto));
    }

}
