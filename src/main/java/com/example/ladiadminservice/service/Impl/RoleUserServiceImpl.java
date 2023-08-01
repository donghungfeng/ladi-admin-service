package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.entity.RoleUser;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleUserRepository;
import com.example.ladiadminservice.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUser> implements RoleUserService {
    @Autowired
    RoleUserRepository roleUserRepository;
    @Override
    protected BaseRepository<RoleUser> getRepository() {
        return roleUserRepository;
    }

    @Override
    public List<RoleUser> getAllByUserId(Long id) {
        return roleUserRepository.findAllByUserId(id);
    }
}
