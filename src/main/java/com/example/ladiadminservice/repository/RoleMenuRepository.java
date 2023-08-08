package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.RoleMenu;

import java.util.List;

public interface RoleMenuRepository extends BaseRepository<RoleMenu> {

    List<RoleMenu> getByRole_IdAndMenu_StatusAndStatus(Long roleId, Integer menuId, Integer status);
}
