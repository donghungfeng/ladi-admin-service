package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.User;
import com.example.ladiadminservice.reponse.BaseResponse;
import com.example.ladiadminservice.request.CreateUserRequest;
import com.example.ladiadminservice.request.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface UserService extends BaseService<User>{
    public BaseResponse createUser(CreateUserRequest createUserRequest) throws NoSuchAlgorithmException;
    public BaseResponse login(LoginRequest loginRequest, String unit) throws NoSuchAlgorithmException;
}
