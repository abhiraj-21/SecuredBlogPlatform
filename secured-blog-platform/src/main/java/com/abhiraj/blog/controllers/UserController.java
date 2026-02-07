package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(
        name = "Users",
        description = "Endpoints for retrieving authenticated user information"
)
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Retrieve the currently authenticated user's profile")
    public ResponseEntity<UserResponseDto> getUser(){
        return ResponseEntity.ok(userService.getUser());
    }

}
