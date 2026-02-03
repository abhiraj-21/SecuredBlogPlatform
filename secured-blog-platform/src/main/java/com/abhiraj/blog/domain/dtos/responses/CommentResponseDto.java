package com.abhiraj.blog.domain.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private String commenterUsername;
    private LocalDateTime createdAt;
}
