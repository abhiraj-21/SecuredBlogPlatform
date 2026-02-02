package com.abhiraj.blog.services;


import com.abhiraj.blog.domain.dtos.requests.PostRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    PostResponseDto addNewPost(PostRequestDto postRequestDto);
    PostResponseDto getPostById(Long id);
    List<PostResponseDto> getAllPosts(Pageable pageable, String search);
}
