package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AdviceController {
    @ExceptionHandler(Exception.class)
    public BaseResponse handleAnotherException(Exception ex) {
        log.info("handleAnotherException: {}", ex.getMessage());
        ex.printStackTrace();
        return BaseResponse.error(ex.getMessage());
    }
}   
