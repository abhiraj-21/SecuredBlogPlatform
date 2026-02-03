package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import com.abhiraj.blog.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> addNewComment(@Valid @RequestBody CommentRequestDto commentRequestDto,
                                                            @PathVariable Long postId){
        return ResponseEntity.ok(commentService.addComment(commentRequestDto, postId));
    }
}
