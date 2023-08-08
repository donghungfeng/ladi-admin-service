package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PermissionRepository;
import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Permission> findByIds(List<Long> ids) {
        return permissionRepository.getByIdInAndStatus(ids, Status.ACTIVE);
    }
}
