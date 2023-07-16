package com.example.ladiadminservice.config.jwt;

import com.example.ladiadminservice.model.User;
import com.example.ladiadminservice.repository.RoleRepository;
import com.example.ladiadminservice.repository.RoleUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    RoleUserRepository roleUserRepository;

    RoleRepository roleRepository;
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Long roleId = roleUserRepository.findAllByUserId(user.getId()).get(0).getRoleId();
        authorities.add(new SimpleGrantedAuthority(roleRepository.findAllById(roleId).getCode()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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
