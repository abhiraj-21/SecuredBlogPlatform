package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.PostRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createNewPost(@Valid @RequestBody PostRequestDto postRequestDto){
        return ResponseEntity.ok(postService.addNewPost(postRequestDto));
    }

}
