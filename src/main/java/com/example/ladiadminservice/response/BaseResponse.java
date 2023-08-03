package com.example.ladiadminservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private Object result;

    public BaseResponse(int statusCode, String status) {
        this.code = statusCode;
        this.message = status;
    }
}
