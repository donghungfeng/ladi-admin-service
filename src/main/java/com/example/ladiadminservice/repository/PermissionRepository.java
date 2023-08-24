package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends BaseRepository<Permission> {

    List<Permission> getByIdInAndStatus(List<Long> ids, Integer status);

    Optional<Permission> getByCodeAndStatusGreaterThan(String code, Integer status);
}
