package com.abhiraj.blog.services;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId);
    Page<CommentResponseDto> getAllCommentsForAPost(Long postId, Pageable pageable);

    void deleteByCommentId(Long commentId);
}
