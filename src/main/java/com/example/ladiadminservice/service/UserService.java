package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.req.AddUserRoleReq;
import com.example.ladiadminservice.repository.entity.User;
import com.example.ladiadminservice.response.BaseResponse;
import com.example.ladiadminservice.request.CreateUserRequest;
import com.example.ladiadminservice.request.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface UserService extends BaseService<User> {
    BaseResponse createUser(CreateUserRequest createUserRequest);

    BaseResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException;

    void addRole(AddUserRoleReq req);
}
