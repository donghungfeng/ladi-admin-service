package com.example.ladiadminservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private String code;
    private String userName;
    private String password;
    private String email;
    private String address;
    private Long unitId;
}
