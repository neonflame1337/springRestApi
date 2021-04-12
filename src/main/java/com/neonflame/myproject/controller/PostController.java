package com.neonflame.myproject.controller;

import com.neonflame.myproject.dto.PostCreateDto;
import com.neonflame.myproject.dto.PostDto;
import com.neonflame.myproject.model.Post;
import com.neonflame.myproject.repository.PostRepo;
import com.neonflame.myproject.repository.UserRepo;
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

    protected final PostRepo postRepo;
    protected final UserRepo userRepo;

    public PostController(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public ResponseEntity<Collection<PostDto>> showAllPost() {
        List<Post> posts = postRepo.findAll();
        if (!posts.isEmpty()) {
            return new ResponseEntity<>(posts.stream()
                    .map(PostDto::new)
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> showPost(@PathVariable("id") long id) {
        Post res = postRepo.findById(id);
        if (res != null)
            return new ResponseEntity<>(new PostDto(res), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> doPost(@RequestBody PostCreateDto postCreateDto,
                                          Principal principal) {
        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setUser(userRepo.findByUsername(principal.getName()));
        postRepo.save(post);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PostDto> delPost(@PathVariable long id,
                                           Principal principal) {
        Post post = postRepo.findById(id);
        if (post != null){
            if (post.getUser().getUsername().equals(principal.getName())) {
                postRepo.delete(post);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
