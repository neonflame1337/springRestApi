package com.neonflame.myproject.security;

import com.neonflame.myproject.model.Role;
import com.neonflame.myproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetailsFactory {
    public static MyUserDetails create(User user) {
        return new MyUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPass(),
                mapToGrantedAuthority(new ArrayList<>(user.getRoles())),
                user.isActive()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority (List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}
