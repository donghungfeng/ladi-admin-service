package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.User;
import com.example.ladiadminservice.reponse.BaseResponse;
import com.example.ladiadminservice.request.CreateUserRequest;
import com.example.ladiadminservice.request.LoginRequest;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("user")
public class UserController extends BaseController<User> {
    @Autowired
    UserService userService;
    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {
        return userService.createUser(createUserRequest);
    }

    @PostMapping("login")
    public BaseResponse login(@RequestBody LoginRequest loginRequest, @RequestParam String unit) throws NoSuchAlgorithmException {
        return userService.login(loginRequest, unit);
    }


}
