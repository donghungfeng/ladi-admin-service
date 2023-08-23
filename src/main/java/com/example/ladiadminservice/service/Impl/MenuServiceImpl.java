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
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public Menu create(Menu menu) throws Exception {
        validateDuplicateCode(menu.getCode());
        return super.create(menu);
    }

    @Override
    public Menu update(Menu req) throws Exception {
        Menu entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<Menu> menuOptional = menuRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (menuOptional.isPresent())
            throw new Exception(String.format("Dữ liệu có mã %s đã tồn tại!", code));
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
