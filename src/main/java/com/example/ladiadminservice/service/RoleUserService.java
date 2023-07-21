package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.RoleUser;

import java.util.List;

public interface RoleUserService extends BaseService<RoleUser> {
    List<RoleUser> getAllByUserId(Long id);
}
