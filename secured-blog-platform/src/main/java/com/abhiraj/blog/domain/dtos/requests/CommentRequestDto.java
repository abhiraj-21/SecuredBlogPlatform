package com.abhiraj.blog.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentRequestDto {
    @NotBlank(message = "Comment cannot be empty.")
    private String content;
}
