package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.model.RoleUser;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role_user")
public class RoleUserController extends BaseController<RoleUser> {
    @Autowired
    RoleUserService roleUserService;
    @Override
    protected BaseService<RoleUser> getService() {
        return roleUserService;
    }
}
