package com.neonflame.myproject.controller;

import com.neonflame.myproject.dto.*;
import com.neonflame.myproject.model.Post;
import com.neonflame.myproject.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/post")
@PreAuthorize("hasAnyAuthority('USER')")
public class PostController {

    protected final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<PostDto>> showAllPost() {
        List<Post> posts = postService.findAll();
        return new ResponseEntity<>(posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostExtDto> showPost(@PathVariable("id") long id) {
        Post res = postService.findPost(id);
        return new ResponseEntity<>(new PostExtDto(res), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> doPost(@RequestBody PostCreateDto postCreateDto,
                                          Principal principal) {
        postService.create(postCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PostDto> delPost(@PathVariable long id,
                                           Principal principal) {
        postService.deletePost(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentDto> addComment(@PathVariable long postId,
                                                 @RequestBody CommentCreateDto commentCreateDto,
                                                 Principal principal) {
        commentCreateDto.setPostId(postId);
        commentCreateDto.setUsername(principal.getName());
        postService.addComment(commentCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable long commentId,
                                                    Principal principal) {
        postService.deleteComment(commentId, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
