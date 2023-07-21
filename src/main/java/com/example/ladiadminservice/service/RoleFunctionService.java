package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.RoleFunction;

import java.util.List;

public interface RoleFunctionService extends BaseService<RoleFunction>{
    List<RoleFunction> getAllByInRoleId(List<Long> roleIdList);
}
