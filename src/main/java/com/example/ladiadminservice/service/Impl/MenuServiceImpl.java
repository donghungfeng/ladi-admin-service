package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.AssignMenuPermissionReq;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.MenuRepository;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.MenuPermission;
import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.MenuPermissionService;
import com.example.ladiadminservice.service.MenuService;
import com.example.ladiadminservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {
    @Autowired
    private MenuPermissionService menuPermissionService;
    @Autowired
    private PermissionService permissionService;
    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    protected BaseRepository<Menu> getRepository() {
        return menuRepository;
    }

    @Override
    public List<Menu> findByIds(List<Long> ids) {
        return menuRepository.getByIdInAndStatus(ids, Status.ACTIVE);
    }

    @Override
    public void assignPermissions(AssignMenuPermissionReq req) throws Exception {
        Menu menu = this.getById(req.getMenuId());
        menuPermissionService.deleteByMenuId(menu.getId());
        if (CollectionUtils.isEmpty(req.getPermissionIds())) return;

        List<Permission> permissions = permissionService.findByIds(req.getPermissionIds());
        if (CollectionUtils.isEmpty(permissions)) return;

        List<MenuPermission> menuPermissions = permissions.stream()
                .map(e -> MenuPermission.builder()
                        .menu(menu)
                        .permission(e)
                        .build())
                .collect(Collectors.toList());
        menuPermissionService.createAll(menuPermissions);
    }
}
