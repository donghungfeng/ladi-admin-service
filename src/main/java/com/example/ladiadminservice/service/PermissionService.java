package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.Permission;

import java.util.List;

public interface PermissionService extends BaseService<Permission> {
    List<Permission> findByIds(List<Long> ids);
}
