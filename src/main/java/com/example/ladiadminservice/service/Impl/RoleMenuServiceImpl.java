package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleMenuRepository;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.repository.entity.RoleMenu;
import com.example.ladiadminservice.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements RoleMenuService {

    private final RoleMenuRepository roleMenuRepository;

    public RoleMenuServiceImpl(RoleMenuRepository roleMenuRepository) {
        this.roleMenuRepository = roleMenuRepository;
    }

    @Override
    protected BaseRepository<RoleMenu> getRepository() {
        return roleMenuRepository;
    }

    @Override
    public void deleteByRole(Long roleId) {
        List<RoleMenu> roleMenus = this.getByRoleId(roleId);
        roleMenus.forEach(e -> e.setStatus(Status.DELETED));
        roleMenuRepository.saveAll(roleMenus);
    }

    @Override
    public List<Menu> getMenusByRoleId(Long roleId) {
        return this.getByRoleId(roleId)
                .stream()
                .map(RoleMenu::getMenu)
                .collect(Collectors.toList());
    }

    private List<RoleMenu> getByRoleId(Long roleId) {
        return roleMenuRepository.getByRole_IdAndMenu_StatusAndStatus(roleId, Status.ACTIVE, Status.ACTIVE);
    }
}
