package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UnitPackageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/unitpackage")
public class UnitPackageController extends BaseController<UnitPackage> {

    private final UnitPackageService unitPackageService;

    public UnitPackageController(UnitPackageService unitPackageService) {
        this.unitPackageService = unitPackageService;
    }

    @Override
    protected BaseService<UnitPackage> getService() {
        return unitPackageService;
    }
}
