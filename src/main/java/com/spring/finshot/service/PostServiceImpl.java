package com.spring.finshot.service;

import com.spring.finshot.entity.Post;
import com.spring.finshot.exception.ResourceNotFound;
import com.spring.finshot.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> findAllActivePosts() {
        return postRepository.findAllActivePosts();
    }

    @Override
    public List<Post> findPublishedPosts() {
        return postRepository.findAllPublishedPosts();
    }

    @Override
    public Post findPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id " + id));
        post.setViews(post.getViews() + 1);

        return post;
    }

    @Override
    public Post createPost(Post payload) {
        Post post = new Post();
        post.setTitle(payload.getTitle());
        post.setDescription(payload.getDescription());
        post.setContent(payload.getContent());
        post.setAuthor(payload.getAuthor());
        post.setViews(0);
        post.setPublished(false);
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post payload) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id " + id));

        post.setTitle(payload.getTitle());
        post.setDescription(payload.getDescription());
        post.setContent(payload.getContent());
        post.setAuthor(payload.getAuthor());
        post.setPublished(payload.isPublished());
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post not found with id " + id));
        post.setDeletedAt(LocalDateTime.now());

        postRepository.save(post);
    }
}
