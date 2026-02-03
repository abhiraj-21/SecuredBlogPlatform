package com.abhiraj.blog.repositories.projections;

import java.time.LocalDateTime;

public interface CommentView {
    Long getId();
    String getContent();
    String getCommenterUsername();
    LocalDateTime getCreatedAt();
}
