package com.example.ladiadminservice.config.jwt;

import com.example.ladiadminservice.repository.entity.User;
import com.example.ladiadminservice.repository.UserRepository;
import com.example.ladiadminservice.service.RoleService;
import com.example.ladiadminservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleService roleUserService;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(roleUserService, roleService, userOptional.get());
    }
}
