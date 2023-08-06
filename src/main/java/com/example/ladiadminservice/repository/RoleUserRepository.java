package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.UserRole;

import java.util.List;

public interface RoleUserRepository extends BaseRepository<UserRole> {
    List<UserRole> findByUser_IdAndRole_StatusAndStatus(Long id, Integer roleStatus, Integer status);
}
