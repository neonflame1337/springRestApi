package com.neonflame.myproject.service;

import com.neonflame.myproject.exeption.UserExistException;
import com.neonflame.myproject.model.User;
import com.neonflame.myproject.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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
