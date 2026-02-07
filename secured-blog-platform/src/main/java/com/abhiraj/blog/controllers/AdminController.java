package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.responses.UserResponseDto;
import com.abhiraj.blog.services.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(
        name = "Admin",
        description = "Administrative operations for managing users and system resources"
)
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    @Operation(summary = "Retrieve all registered users with pagination")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                                             @RequestParam(required = false, defaultValue = "1") int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(adminService.getAllRegisteredUsers(pageable));
    }

}
