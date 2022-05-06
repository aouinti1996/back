package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.CommentPost;
import com.springheroes.wellco.services.CommentPostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/commentPost")
@AllArgsConstructor
public class CommentPostController {
    private final CommentPostService commentPostService;

    @PostMapping("/{postId}")
    public CommentPost createCommentPost(@PathVariable Long postId, @RequestBody String content){
        return commentPostService.createCommentPost(postId, content);
    }
    @PutMapping
    public CommentPost updateCommentPost(@RequestBody CommentPost commentPost){
        return commentPostService.updateCommentPost(commentPost);
    }
    @GetMapping
    public List<CommentPost> getAllCommentPost(){
        return commentPostService.getAllCommentPost();
    }
    @GetMapping("/{id}")
    public CommentPost getCommentPostById(@PathVariable Long id){
        return commentPostService.getCommentPostById(id);
    }

    @GetMapping("/byPostId/{postId}")
    public List<CommentPost> getAlCommentsByPostId(@PathVariable Long postId) {
        return commentPostService.getCommentsByPostId(postId);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentPostById(@PathVariable Long id){
        commentPostService.deleteCommentPostById(id);
    }

}