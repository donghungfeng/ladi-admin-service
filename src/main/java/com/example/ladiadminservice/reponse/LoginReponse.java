package com.example.ladiadminservice.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginReponse {
    private String token;
    private List<String> roleList;
    private List<String> urlList;
}
