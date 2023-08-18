package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.PermissionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController<Permission> {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected BaseService<Permission> getService() {
        return permissionService;
    }
}
