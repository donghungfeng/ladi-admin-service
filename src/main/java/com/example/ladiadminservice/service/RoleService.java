package com.example.ladiadminservice.service;


import com.example.ladiadminservice.repository.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> getAllByInId(List<Long> idList);
}
