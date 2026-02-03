package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.requests.PostCreateRequestDto;
import com.abhiraj.blog.domain.dtos.requests.PostUpdateRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.ForbiddenOperationException;
import com.abhiraj.blog.exceptions.PostNotFoundException;
import com.abhiraj.blog.mappings.PostMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.projections.PostCommentCount;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.CurrentUserService;
import com.abhiraj.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostMapping postMapping;
    private final CurrentUserService currentUserService;

    @Override
    public PostResponseDto addNewPost(PostCreateRequestDto postCreateRequestDto) {

        User user = currentUserService.getAuthenticatedUser();

        Post post = postMapping.createRequestToPost(postCreateRequestDto);
        post.setAuthor(user);
        Post savedPost = postRepository.save(post);
        Long commentCount = commentRepository.countByPostId(savedPost.getId());
        return postMapping.postToResponse(savedPost, commentCount);
    }

    @Override
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("No post found with id: " + id)
        );
        return postMapping.postToResponse(post, commentRepository.countByPostId(id));
    }

    @Override
    public List<PostResponseDto> getAllPosts(Pageable pageable, String search) {
        List<Post> posts = new ArrayList<>();
        if (search == null || search.isBlank()) {
            posts = postRepository.findAll(pageable).getContent();
        }else {
            posts = postRepository.findByAuthorUsername(search, pageable).getContent();
        }

        //If we will use commentRepository.countByPostId() for every time we convert post to response we would face N+1 query problem
        //So we have mapped post with their commentCount and now for every post we can look up in O(1)
        List<Long> postIds = posts.stream()
                .map(Post::getId)
                .toList();

        List<PostCommentCount> commentCounts = commentRepository.getCommentsByPostIds(postIds);

        Map<Long, Long> commentCountMap = commentCounts.stream()
                .collect(
                        Collectors.toMap(
                                PostCommentCount::getPostId,
                                PostCommentCount::getCommentCount
                        )
                );

        return posts.stream()
                .map(post -> postMapping.postToResponse(
                        post,
                        commentCountMap.getOrDefault(post.getId(), 0L))
                )
                .toList();
    }

    @Override
    public PostResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException("No post with post id "+ id)
        );

        User currentUser = currentUserService.getAuthenticatedUser();
        if(!post.getAuthor().getId().equals(currentUser.getId())){
            throw new ForbiddenOperationException("You cannot edit someone else's post!!");
        }

        postMapping.applyUpdates(post, postUpdateRequestDto);
        postRepository.save(post);
        return postMapping.postToResponse(post, commentRepository.countByPostId(id));
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                    new PostNotFoundException("No post with post id "+id)
                );
        User currentUser = currentUserService.getAuthenticatedUser();
        if(!post.getAuthor().getId().equals(currentUser.getId())){
            throw new ForbiddenOperationException("You cannot delete someone else's post!!");
        }
        postRepository.delete(post);
    }
}
