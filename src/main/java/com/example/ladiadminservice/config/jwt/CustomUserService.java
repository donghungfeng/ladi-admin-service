package com.example.ladiadminservice.config.jwt;

import com.example.ladiadminservice.model.User;
import com.example.ladiadminservice.repository.RoleRepository;
import com.example.ladiadminservice.repository.RoleUserRepository;
import com.example.ladiadminservice.repository.UserRepository;
import com.example.ladiadminservice.service.RoleService;
import com.example.ladiadminservice.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleUserService roleUserService;

    @Autowired
    RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails( roleUserService, roleService, user);
    }
}
