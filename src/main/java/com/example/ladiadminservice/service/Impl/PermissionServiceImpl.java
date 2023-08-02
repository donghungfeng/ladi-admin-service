package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PermissionRepository;
import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected BaseRepository<Permission> getRepository() {
        return permissionRepository;
    }
}
