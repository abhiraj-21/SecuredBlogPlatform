package com.abhiraj.blog.mappings;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import com.abhiraj.blog.domain.entities.Comment;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.repositories.projections.CommentView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentMapping {

    public Comment requestToComment(CommentRequestDto commentRequestDto, Post post, User commenter){
        return Comment.builder()
                .commenter(commenter)
                .post(post)
                .content(commentRequestDto.getContent())
                .build();
    }

    public CommentResponseDto viewToResponse(CommentView commentView){
        return CommentResponseDto.builder()
                .commentId(commentView.getId())
                .content(commentView.getContent())
                .commenterUsername(commentView.getCommenterUsername())
                .createdAt(commentView.getCreatedAt())
                .build();
    }

    public CommentResponseDto commentToResponse(Comment comment){
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .commenterUsername(comment.getCommenter().getUsername())
                .createdAt(comment.getCreatedAt())
                .content(comment.getContent())
                .build();
    }

}
