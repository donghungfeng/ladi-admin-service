package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.AssignRoleMenuReq;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleRepository;
import com.example.ladiadminservice.repository.entity.RoleMenu;
import com.example.ladiadminservice.service.MenuService;
import com.example.ladiadminservice.service.RoleMenuService;
import com.example.ladiadminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
    public void assignMenu(AssignRoleMenuReq req) {
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
}

