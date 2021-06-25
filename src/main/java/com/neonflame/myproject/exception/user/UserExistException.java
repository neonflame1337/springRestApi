package com.neonflame.myproject.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "This user already exists")
public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
