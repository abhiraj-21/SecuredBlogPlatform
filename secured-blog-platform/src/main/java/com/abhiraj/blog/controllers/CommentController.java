package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import com.abhiraj.blog.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Page<CommentResponseDto>> getCommentsByPostId(@PathVariable Long postId,
                                                                        @RequestParam(required = false, defaultValue = "2") int pageSize,
                                                                        @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                                        @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
                                                                        @RequestParam(required = false, defaultValue = "desc") String sortOrder){
        Sort sort = null;
        if(sortOrder.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return ResponseEntity.ok(commentService.getAllCommentsForAPost(postId, pageable));
    }

    @DeleteMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long commentId){
        commentService.deleteByCommentId(commentId);
    }
}
