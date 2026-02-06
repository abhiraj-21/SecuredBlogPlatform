package com.abhiraj.blog.repositories;

import com.abhiraj.blog.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("""
            SELECT u,
                (SELECT COUNT(p) FROM posts p WHERE p.author = u),
                (SELECT COUNT(c) FROM comments c WHERE c.commenter = u)
            FROM users u
            """)
    Page<Object[]> findAllUsersWithCount(Pageable pageable);

}
