package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.model.RoleUser;

import java.util.List;

public interface RoleUserRepository extends BaseRepository<RoleUser>{
    List<RoleUser> findAllByUserId(Long id);
}
