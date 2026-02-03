package com.abhiraj.blog.mappings;

import com.abhiraj.blog.domain.dtos.requests.PostCreateRequestDto;
import com.abhiraj.blog.domain.dtos.requests.PostUpdateRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.domain.entities.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostMapping {

    public Post createRequestToPost(PostCreateRequestDto postCreateRequestDto){
        return Post.builder()
                .title(postCreateRequestDto.getTitle())
                .content(postCreateRequestDto.getContent())
                .build();
    }

    public PostResponseDto postToResponse(Post post, Long commentCount){
        return PostResponseDto.builder()
                .postId(post.getId())
                .authorUsername(post.getAuthor().getUsername())
                .commentCount(commentCount)
                .content(post.getContent())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public void applyUpdates(Post post, PostUpdateRequestDto postUpdateRequestDto){
        String title = postUpdateRequestDto.getTitle();
        String content = postUpdateRequestDto.getContent();
        if(title != null){
            post.setTitle(title);
        }
        if(content != null){
            post.setContent(content);
        }
    }

}
