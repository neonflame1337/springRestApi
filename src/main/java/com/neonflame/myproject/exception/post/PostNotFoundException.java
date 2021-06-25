package com.neonflame.myproject.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Post not found")
public class PostNotFoundException extends  RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
