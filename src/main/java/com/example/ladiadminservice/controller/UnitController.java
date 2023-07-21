package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.model.Unit;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("unit")
public class UnitController extends BaseController<Unit>{
    @Autowired
    UnitService unitService;
    @Override
    protected BaseService<Unit> getService() {
        return unitService;
    }
}