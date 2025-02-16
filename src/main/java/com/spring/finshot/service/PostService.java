package com.spring.finshot.service;

import com.spring.finshot.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> findAllActivePosts();
    List<Post> findPublishedPosts();
    Post findPostById(Long id);
    Post createPost(Post payload);
    Post updatePost(Long id, Post payload);
    void deletePost(Long id);
}
