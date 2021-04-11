package com.neonflame.myproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String pass;
    private final List<? extends GrantedAuthority> authority;
    private final boolean active;

    public MyUserDetails(Long id, String username, String pass, List<? extends GrantedAuthority> authority, boolean active) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.authority = authority;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
