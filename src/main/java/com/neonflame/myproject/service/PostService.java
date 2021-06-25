package com.neonflame.myproject.service;

import com.neonflame.myproject.dto.CommentCreateDto;
import com.neonflame.myproject.dto.PostCreateDto;
import com.neonflame.myproject.exception.post.PostAccessDeniedException;
import com.neonflame.myproject.exception.post.PostNotFoundException;
import com.neonflame.myproject.model.Comment;
import com.neonflame.myproject.model.Post;
import com.neonflame.myproject.model.Role;
import com.neonflame.myproject.model.User;
import com.neonflame.myproject.repository.CommentRepo;
import com.neonflame.myproject.repository.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepo postRepo;
    private final UserService userService;
    private final CommentRepo commentRepo;

    public PostService(PostRepo postRepo, UserService userService, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.userService = userService;
        this.commentRepo = commentRepo;
    }

    public List<Post> findAll() {
        List<Post> posts = postRepo.findAll();
        if (posts.isEmpty())
            throw new PostNotFoundException("Any posts was not found");
        return posts;
    }

    public Post findPost(long id) {
        Post post = postRepo.findById(id);
        if (post == null)
            throw new PostNotFoundException("Post was not found");
        return post;
    }

    public Comment findComment(long id) {
        Comment comment = commentRepo.findById(id);
        if (comment == null)
            throw new PostNotFoundException("Comment was not found");
        return comment;
    }

    public Post create(PostCreateDto postCreateDto) {
        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setUser(userService.findUserByUsername(postCreateDto.getUsername()));
        return postRepo.save(post);
    }

    public void deletePost(long id, String username) {
        Post post = findPost(id);
        User user = userService.findUserByUsername(username);
        if (!post.getUser().equals(username) && !user.getRoles().contains(Role.ADMIN))
            throw new PostAccessDeniedException("User can not delete the post");
        postRepo.delete(post);
    }

    public Comment addComment(CommentCreateDto commentCreateDto) {
        Comment comment = new Comment();
        comment.setPost(findPost(commentCreateDto.getPostId()));
        comment.setUser(userService.findUserByUsername(commentCreateDto.getUsername()));
        comment.setContent(commentCreateDto.getContent());
        return commentRepo.save(comment);
    }

    public void deleteComment(long commentId, String username) {
        Comment comment = findComment(commentId);
        User user = userService.findUserByUsername(username);
        if (!(comment.getUser().getUsername().equals(username) || user.getRoles().contains(Role.ADMIN)))
            throw new PostAccessDeniedException("User can not delete the comment");
        commentRepo.delete(comment);
    }
}
