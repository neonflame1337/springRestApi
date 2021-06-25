package com.neonflame.myproject.controller;

import com.neonflame.myproject.dto.UserDto;
import com.neonflame.myproject.dto.UserRegDto;
import com.neonflame.myproject.model.User;
import com.neonflame.myproject.repository.UserRepo;
import com.neonflame.myproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminUserController {

    private final UserRepo userRepo;
    private final UserService userService;

    public AdminUserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<UserDto>> showAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> showUser(@PathVariable("id") long id) {
        User res = userService.findUserById(id);
        return new ResponseEntity<>(new UserDto(res), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> addUser(@RequestBody UserRegDto userReg) {
        return new ResponseEntity<>(
                new UserDto(userService.register(userReg.getUsername(), userReg.getPass())),
                HttpStatus.OK);
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
