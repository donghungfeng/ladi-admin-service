package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.model.Role;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleRepository;
import com.example.ladiadminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    protected BaseRepository<Role> getRepository() {
        return roleRepository;
    }
}

