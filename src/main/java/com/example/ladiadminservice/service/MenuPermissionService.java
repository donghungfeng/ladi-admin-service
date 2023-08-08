package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.MenuPermission;
import com.example.ladiadminservice.repository.entity.Permission;

import java.util.List;

public interface MenuPermissionService extends BaseService<MenuPermission> {

    void deleteByMenuId(Long menuId);

    List<Permission> getPermissionsByMenuId(Long menuId);
}
