package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.MenuPermission;

import java.util.List;

public interface MenuPermissionRepository extends BaseRepository<MenuPermission> {

    List<MenuPermission> getByMenu_IdAndPermission_StatusAndStatus(Long menuId, Integer permissionStatus, Integer status);
}
