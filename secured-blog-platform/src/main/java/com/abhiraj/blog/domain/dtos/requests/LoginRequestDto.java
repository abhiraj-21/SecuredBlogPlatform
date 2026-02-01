package com.abhiraj.blog.domain.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequestDto {
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please follow the global email format")
    private String email;
}
