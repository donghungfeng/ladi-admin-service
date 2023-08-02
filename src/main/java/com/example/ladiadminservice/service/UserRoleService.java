package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole> {
    List<UserRole> getAllByUserId(Long id);

    void deleteByUser(Long userId);
}
