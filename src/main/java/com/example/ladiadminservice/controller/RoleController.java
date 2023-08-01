package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role> {
    @Autowired
    RoleService roleService;
    @Override
    protected BaseService<Role> getService() {
        return roleService;
    }
}
