package com.abhiraj.blog.repositories.projections;

public interface PostCommentCount {
    Long getPostId();
    Long getCommentCount();
}
