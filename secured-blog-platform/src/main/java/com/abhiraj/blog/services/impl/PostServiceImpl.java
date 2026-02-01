package com.abhiraj.blog.services.impl;

import com.abhiraj.blog.domain.dtos.requests.PostRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.mappings.PostMapping;
import com.abhiraj.blog.repositories.CommentRepository;
import com.abhiraj.blog.repositories.PostRepository;
import com.abhiraj.blog.repositories.UserRepository;
import com.abhiraj.blog.services.CurrentUserService;
import com.abhiraj.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostMapping postMapping;
    private final CurrentUserService authUtil;

    @Override
    public PostResponseDto addNewPost(PostRequestDto postRequestDto) {

        User user = authUtil.getAuthenticatedUser();

        Post post = postMapping.requestToPost(postRequestDto);
        post.setAuthor(user);
        Post savedPost = postRepository.save(post);
        Long commentCount = commentRepository.countByPostId(savedPost.getId());
        return postMapping.postToResponse(savedPost, commentCount);
    }
}
