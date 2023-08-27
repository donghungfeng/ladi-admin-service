package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.AssignRoleMenuReq;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleRepository;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.repository.entity.RoleMenu;
import com.example.ladiadminservice.service.MenuService;
import com.example.ladiadminservice.service.RoleMenuService;
import com.example.ladiadminservice.service.RoleService;
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
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    @Override
    protected BaseRepository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public List<Role> getAllByInId(List<Long> idList) {
        return roleRepository.findByIdInAndStatus(idList, Status.ACTIVE);
    }

    @Override
    public void assignMenu(AssignRoleMenuReq req) throws Exception {
        Role role = this.getById(req.getRoleId());
        roleMenuService.deleteByRole(role.getId());
        if (CollectionUtils.isEmpty(req.getMenuIds())) return;
        List<Menu> menus = menuService.findByIds(req.getMenuIds());
        if (CollectionUtils.isEmpty(menus)) return;

        List<RoleMenu> roleMenus = menus.stream()
                .map(e -> RoleMenu.builder()
                        .role(role)
                        .menu(e)
                        .build())
                .collect(Collectors.toList());
        roleMenuService.createAll(roleMenus);
    }

    @Override
    public Role create(Role req) throws Exception {
        validateDuplicateCode(req.getCode());
        return super.create(req);
    }

    @Override
    public Role update(Role req) throws Exception {
        Role entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<Role> entityOptional = roleRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (entityOptional.isPresent())
            throw new Exception(String.format("Dữ liệu có mã %s đã tồn tại!", code));
    }
}

