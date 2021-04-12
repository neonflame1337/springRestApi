package com.neonflame.myproject.controller;

import com.neonflame.myproject.dto.UserDto;
import com.neonflame.myproject.model.User;
import com.neonflame.myproject.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {

    private final UserRepo userRepo;

    public AdminUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public ResponseEntity<Collection<UserDto>> showAllUsers() {
        List<User> users = userRepo.findAll();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users.stream()
                    .map(UserDto::new)
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> showUser(@PathVariable("id") long id) {
        User res = userRepo.findById(id);
        if (res != null)
            return new ResponseEntity<>(new UserDto(res), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public String addUser() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") long id) {
        User res = userRepo.findById(id);
        if (res != null) {
            userRepo.delete(res);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
