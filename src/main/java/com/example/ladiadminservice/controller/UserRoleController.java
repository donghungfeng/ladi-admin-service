package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.repository.entity.UserRole;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role_user")
public class UserRoleController extends BaseController<UserRole> {
    @Autowired
    UserRoleService roleUserService;

    @Override
    protected BaseService<UserRole> getService() {
        return roleUserService;
    }
}
