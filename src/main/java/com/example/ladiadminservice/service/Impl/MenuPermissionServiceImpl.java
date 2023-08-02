package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.MenuPermissionRepository;
import com.example.ladiadminservice.repository.entity.MenuPermission;
import com.example.ladiadminservice.service.MenuPermissionService;
import org.springframework.stereotype.Service;

@Service
public class MenuPermissionServiceImpl extends BaseServiceImpl<MenuPermission> implements MenuPermissionService {

    private final MenuPermissionRepository permissionRepository;

    public MenuPermissionServiceImpl(MenuPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected BaseRepository<MenuPermission> getRepository() {
        return permissionRepository;
    }
}
