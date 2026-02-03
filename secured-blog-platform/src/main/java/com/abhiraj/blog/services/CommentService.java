package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface CommentService {
    @Transactional
    CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId);
}
