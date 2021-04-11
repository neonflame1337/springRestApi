package com.neonflame.myproject.controller;

import com.neonflame.myproject.exeption.UserExistException;
import com.neonflame.myproject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String main(Principal principal) {
        String name = principal.getName();
        return "Hello world!" + "\n" + name;
    }

}
