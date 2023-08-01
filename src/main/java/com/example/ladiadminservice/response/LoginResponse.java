package com.example.ladiadminservice.response;

import com.example.ladiadminservice.repository.entity.Function;
import com.example.ladiadminservice.repository.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String token;
    private List<Role> roleList;
    private List<Function> functionList;
}
