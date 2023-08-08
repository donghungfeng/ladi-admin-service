package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.req.AssignUserRoleReq;
import com.example.ladiadminservice.repository.entity.User;
import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface UserService extends BaseService<User> {

    BaseResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException;

    void assignRole(AssignUserRoleReq req) throws Exception;
}
