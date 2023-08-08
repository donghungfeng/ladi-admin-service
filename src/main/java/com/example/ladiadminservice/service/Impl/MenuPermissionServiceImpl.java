package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.MenuPermissionRepository;
import com.example.ladiadminservice.repository.entity.MenuPermission;
import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.MenuPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuPermissionServiceImpl extends BaseServiceImpl<MenuPermission> implements MenuPermissionService {

    private final MenuPermissionRepository menuPermissionRepository;

    public MenuPermissionServiceImpl(MenuPermissionRepository menuPermissionRepository) {
        this.menuPermissionRepository = menuPermissionRepository;
    }

    @Override
    protected BaseRepository<MenuPermission> getRepository() {
        return menuPermissionRepository;
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        List<MenuPermission> menuPermissions = this.getByMenuId(menuId);
        menuPermissions.forEach(e -> e.setStatus(Status.DELETED));
        menuPermissionRepository.saveAll(menuPermissions);
    }

    @Override
    public List<Permission> getPermissionsByMenuId(Long menuId) {
        return this.getByMenuId(menuId)
                .stream()
                .map(MenuPermission::getPermission)
                .collect(Collectors.toList());
    }

    private List<MenuPermission> getByMenuId(Long menuId) {
        return menuPermissionRepository.getByMenu_IdAndPermission_StatusAndStatus(menuId, Status.ACTIVE, Status.ACTIVE);
    }
}
