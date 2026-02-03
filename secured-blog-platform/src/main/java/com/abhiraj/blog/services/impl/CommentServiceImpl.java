package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import com.abhiraj.blog.domain.entities.Comment;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.PostNotFoundException;
import com.abhiraj.blog.mappings.CommentMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.projections.CommentView;
import com.abhiraj.blog.services.CommentService;
import com.abhiraj.blog.services.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final CommentMapping commentMapping;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostNotFoundException("No post with post id: " + postId)
        );
        User currentUser = currentUserService.getAuthenticatedUser();
        Comment savedComment = commentRepository.save(commentMapping.requestToComment(commentRequestDto, post, currentUser));
        CommentView view = commentRepository.findCommentViewById(savedComment.getId());
        return commentMapping.viewToResponse(view);
    }
}
