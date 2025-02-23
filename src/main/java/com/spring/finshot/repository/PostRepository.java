package com.spring.finshot.repository;

import com.spring.finshot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.published = true and p.deletedAt IS NULL")
    List<Post> findAllPublishedPosts();

    @Query("SELECT p FROM Post p WHERE p.deletedAt IS NULL")
    List<Post> findAllActivePosts();
}
