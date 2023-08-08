package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.RoleMenu;

import java.util.List;

public interface RoleMenuService extends BaseService<RoleMenu> {

    void deleteByRole(Long roleId);

    List<Menu> getMenusByRoleId(Long roleId);
}
