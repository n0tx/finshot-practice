package com.spring.finshot.presenter;

import com.spring.finshot.entity.Post;
import com.spring.finshot.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
public class PostPresenter {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public Map<String, Object> getAllPosts() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", postService.findAllActivePosts());
        return response;
    }

    @GetMapping("/published")
    public Map<String, Object> getAllPublishedPosts() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", postService.findPublishedPosts());
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPostById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", postService.findPostById(id));
        return response;
    }

    @PostMapping
    public Map<String, Object> createPost(@RequestBody Post payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", postService.createPost(payload));
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updatePost(@PathVariable Long id, @RequestBody Post payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", postService.updatePost(id, payload));
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deletePost(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        postService.deletePost(id);
        return response;
    }
}
