package com.abhiraj.blog.domain.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtResponseDto {
    private String token;
}
