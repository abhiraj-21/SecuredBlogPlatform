package com.abhiraj.blog.services;


import com.abhiraj.blog.domain.dtos.requests.PostCreateRequestDto;
import com.abhiraj.blog.domain.dtos.requests.PostUpdateRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    @Transactional
    PostResponseDto addNewPost(PostCreateRequestDto postCreateRequestDto);
    PostResponseDto getPostById(Long id);
    List<PostResponseDto> getAllPosts(Pageable pageable, String search);
    @Transactional
    PostResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto);
    @Transactional
    void deleteById(Long id);
}
