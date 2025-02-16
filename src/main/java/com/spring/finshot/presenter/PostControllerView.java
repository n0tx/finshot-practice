package com.spring.finshot.presenter;

import com.spring.finshot.entity.Post;
import com.spring.finshot.utils.ApiResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostControllerView {
    private final String API_BASE_URL = "http://localhost:8081/api/v1/posts";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public String listPosts(Model model) {
        ApiResponseHelper<Post> response = restTemplate.getForObject(API_BASE_URL, ApiResponseHelper.class);
        model.addAttribute("posts", response.getData());
        return "post-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post-form";
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        restTemplate.postForObject(API_BASE_URL, post, Post.class);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Post post = restTemplate.getForObject(API_BASE_URL + "/" + id, Post.class);
        model.addAttribute("post", post);
        return "post-form";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        String url = UriComponentsBuilder.fromHttpUrl(API_BASE_URL + "/" + id).toUriString();
        restTemplate.put(url, post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        restTemplate.delete(API_BASE_URL + "/" + id);
        return "redirect:/posts";
    }
}
