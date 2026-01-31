package com.abhiraj.blog.mappings;

import com.abhiraj.blog.domain.dtos.requests.PostRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.domain.entities.Post;
import com.abhiraj.blog.domain.entities.User;
import com.abhiraj.blog.exceptions.UserNotFoundException;
import com.abhiraj.blog.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostMapping {

    public Post requestToPost(PostRequestDto postRequestDto){
        return Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .build();
    }

    public PostResponseDto postToResponse(Post post, Long commentCount){
        return PostResponseDto.builder()
                .authorUsername(post.getAuthor().getUsername())
                .commentCount(commentCount)
                .content(post.getContent())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}
