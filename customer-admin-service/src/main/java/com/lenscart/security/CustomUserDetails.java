package com.lenscart.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.authorities = List.of(new SimpleGrantedAuthority(role));
    }
//Returns the list of roles/permissions assigned to the user.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
//Returns the hashed password stored in the database
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
