package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.Role;
import com.abhiraj.blog.domain.dtos.requests.CommentRequestDto;
import com.abhiraj.blog.domain.dtos.responses.CommentResponseDto;
import com.abhiraj.blog.domain.entities.Comment;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.CommentNotFoundException;
import com.abhiraj.blog.exceptions.ForbiddenOperationException;
import com.abhiraj.blog.exceptions.PostNotFoundException;
import com.abhiraj.blog.mappings.CommentMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.services.CommentService;
import com.abhiraj.blog.services.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final CommentMapping commentMapping;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostNotFoundException("No post with post id: " + postId)
        );
        User currentUser = currentUserService.getAuthenticatedUser();
        Comment savedComment = commentRepository.save(commentMapping.requestToComment(commentRequestDto, post, currentUser));
        return commentMapping.commentToResponse(savedComment);
    }

    @Override
    public Page<CommentResponseDto> getAllCommentsForAPost(Long postId, Pageable pageable) {
        Page<Comment> comments = commentRepository.getCommentsByPostId(postId, pageable);
        if (comments.isEmpty() && !postRepository.existsById(postId)) {
            throw new PostNotFoundException("No post with post id "+postId);
        }
        return comments.map(commentMapping::commentToResponse);
    }

    @Override
    @Transactional
    public void deleteByCommentId(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                    new CommentNotFoundException("No comment with comment id "+commentId)
                );
        User currentUser = currentUserService.getAuthenticatedUser();
        boolean isCommentOwner = comment.getCommenter().getId().equals(currentUser.getId());
        boolean isPostOwner = comment.getPost().getAuthor().getId().equals(currentUser.getId());

        if (!isCommentOwner && !isPostOwner && !currentUser.getRoles().contains(Role.ADMIN)) {
            throw new ForbiddenOperationException("You are not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }
}
