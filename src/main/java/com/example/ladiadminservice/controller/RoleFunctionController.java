package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.model.RoleFunction;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role_function")
public class RoleFunctionController extends BaseController<RoleFunction>{
    @Autowired
    RoleFunctionService roleFunctionService;
    @Override
    protected BaseService<RoleFunction> getService() {
        return roleFunctionService;
    }
}
