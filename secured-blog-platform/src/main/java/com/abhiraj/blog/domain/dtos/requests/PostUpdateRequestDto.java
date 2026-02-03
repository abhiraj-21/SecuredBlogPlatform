package com.abhiraj.blog.domain.dtos.requests;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostUpdateRequestDto {
    @Size(min=1, max=255)
    private String title;
    @Size(min=1)
    private String content;
}
