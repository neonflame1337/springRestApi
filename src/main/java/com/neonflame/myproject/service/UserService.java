package com.neonflame.myproject.service;

import com.neonflame.myproject.exception.user.UserExistException;
import com.neonflame.myproject.exception.user.UserNotFoundException;
import com.neonflame.myproject.model.User;
import com.neonflame.myproject.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(long id) {
        User user = userRepo.findById(id);
        if (user == null)
            throw new UserNotFoundException("User not found");
        return user;
    }

    public User findUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User not found");
        return user;
    }

    public List<User> findAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty())
            throw new UserNotFoundException("Any user was not found");
        return users;
    }

    public User register(String username, String pass) throws UserExistException {
        User res = userRepo.findByUsername(username);
        if(res != null) {
            throw new UserExistException("User with username " + " exists");
        }
        else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPass(new BCryptPasswordEncoder(12).encode(pass));
            userRepo.save(newUser);
            return newUser;
        }
    }

}
