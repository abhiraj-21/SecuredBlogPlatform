package com.abhiraj.blog.controllers;

import com.abhiraj.blog.domain.dtos.requests.PostCreateRequestDto;
import com.abhiraj.blog.domain.dtos.requests.PostUpdateRequestDto;
import com.abhiraj.blog.domain.dtos.responses.PostResponseDto;
import com.abhiraj.blog.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
@RequiredArgsConstructor
@Tag(
        name = "Posts",
        description = "Endpoints for creating, retrieving, updating, deleting, and searching blog posts"
)
public class PostController {

    private final PostService postService;

    @PostMapping
    @Operation(summary = "Create a new blog post")
    public ResponseEntity<PostResponseDto> createNewPost(@Valid @RequestBody PostCreateRequestDto postCreateRequestDto){
        return ResponseEntity.ok(postService.addNewPost(postCreateRequestDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a single post by its ID")
    public ResponseEntity<PostResponseDto> getPostByPostId(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts with optional pagination, sorting, and search")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@RequestParam(required = false, defaultValue = "0") int pageNo,          //In which page you are looking at (0-based indexing)
                                                             @RequestParam(required = false, defaultValue = "2") int pageSize,        //Maximum number of posts you would see on a single page
                                                             @RequestParam(required = false, defaultValue = "updatedAt") String sortBy,
                                                             @RequestParam(required = false, defaultValue = "desc") String sortDirection,
                                                             @RequestParam(required = false) String search){
        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);                                                       //Paging is achieved using Pageable interface which is implemented by PageRequest
        return ResponseEntity.ok(postService.getAllPosts(pageable, search));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update an existing post by its ID")
    public ResponseEntity<PostResponseDto> updatePostById(@PathVariable Long id,
                                                          @Valid @RequestBody PostUpdateRequestDto postUpdateRequestDto){
        return ResponseEntity.ok(postService.updatePost(id, postUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a post by its ID")
    public void deletePostById(@PathVariable Long id){
        postService.deleteById(id);
    }

}
