package com.neonflame.myproject.dto;

import com.neonflame.myproject.model.Role;
import com.neonflame.myproject.model.User;

import java.util.List;

public class UserDto {

    private Long id;
    private String username;
    private boolean active;
    private List<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.active = user.isActive();
        this.roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
