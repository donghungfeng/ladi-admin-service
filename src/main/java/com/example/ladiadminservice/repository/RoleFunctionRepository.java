package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.model.RoleFunction;

import java.util.List;

public interface RoleFunctionRepository extends BaseRepository<RoleFunction>{
    List<RoleFunction> findAllByRoleId(Long roleId);
}
