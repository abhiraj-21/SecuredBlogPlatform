package com.abhiraj.blog.services;


import com.abhiraj.blog.domain.dtos.requests.PostRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;

public interface PostService {
    PostResponseDto addNewPost(PostRequestDto postRequestDto);
}
