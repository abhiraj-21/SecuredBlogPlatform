package com.abhiraj.blog.repositories;

import com.abhiraj.blog.domain.entities.Comment;
import com.abhiraj.blog.repositories.projections.CommentView;
import com.abhiraj.blog.repositories.projections.PostCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByPostId(Long postId);
    long countByCommenterId(Long userId);

    @Query("""
            SELECT c.post.id AS postId,
            COUNT(c.id) AS commentCount
            FROM comments c
            WHERE c.post.id IN :postIds
            GROUP BY c.post.id
            """)
    List<PostCommentCount> getCommentsByPostIds(List<Long> postIds);

    @EntityGraph(attributePaths = {"commenter"})
    Page<Comment> getCommentsByPostId(Long postId, Pageable pageable);
}
