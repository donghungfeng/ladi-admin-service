package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Function;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("function")
public class FunctionController extends BaseController<Function> {
    @Autowired
    FunctionService functionService;
    @Override
    protected BaseService<Function> getService() {
        return functionService;
    }
}
