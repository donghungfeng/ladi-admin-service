package com.example.ladiadminservice.service;


import com.example.ladiadminservice.model.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> getAllByInId(List<Long> idList);
}
