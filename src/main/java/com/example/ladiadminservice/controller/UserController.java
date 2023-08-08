package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.req.AssignUserRoleReq;
import com.example.ladiadminservice.repository.entity.User;
import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.LoginRequest;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UserRoleService;
import com.example.ladiadminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        return userService.login(loginRequest);
    }

    @PostMapping("/role/assign")
    public BaseResponse assignUserRole(@RequestBody AssignUserRoleReq req) throws Exception {
        userService.assignRole(req);
        return new BaseResponse().success();
    }

    @GetMapping("/role")
    public BaseResponse getRoleOfUser(@RequestParam Long userId) {
        return new BaseResponse().success(userRoleService.getRolesByUserId(userId));
    }
}
