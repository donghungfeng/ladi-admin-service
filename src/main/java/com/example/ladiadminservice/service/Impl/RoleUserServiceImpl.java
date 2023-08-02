package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.entity.UserRole;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleUserRepository;
import com.example.ladiadminservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {
    @Autowired
    RoleUserRepository roleUserRepository;

    @Override
    protected BaseRepository<UserRole> getRepository() {
        return roleUserRepository;
    }

    @Override
    public List<UserRole> getAllByUserId(Long id) {
        return roleUserRepository.findAllByUserId(id);
    }

    @Override
    public void deleteByUser(Long userId) {
        List<UserRole> userRoles = this.getAllByUserId(userId);
        userRoles.forEach(e -> e.setStatus(Status.DELETED));
        roleUserRepository.saveAll(userRoles);
    }
}
