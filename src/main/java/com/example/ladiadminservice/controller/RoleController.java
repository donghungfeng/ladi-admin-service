package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.AssignRoleMenuReq;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.RoleMenuService;
import com.example.ladiadminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role> {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    protected BaseService<Role> getService() {
        return roleService;
    }

    @PostMapping("/menu/assign")
    BaseResponse assignRoleMenu(@RequestBody AssignRoleMenuReq req) throws Exception {
        roleService.assignMenu(req);
        return new BaseResponse().success();
    }

    @GetMapping("/menu")
    BaseResponse getMenu(@RequestParam Long roleId) {
        return new BaseResponse().success(roleMenuService.getMenusByRoleId(roleId));
    }
}
