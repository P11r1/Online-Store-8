package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Bahadir Tasli
 * @Date 3/22/2023
 */

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private User user;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority author = new SimpleGrantedAuthority(user.getAuthor().getFirstName());
        return Collections.singletonList(author);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getFullName();
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
