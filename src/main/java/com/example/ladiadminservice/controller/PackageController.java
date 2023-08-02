package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.MyPackage;
import com.example.ladiadminservice.service.BaseService;

import com.example.ladiadminservice.service.PackageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class PackageController extends BaseController<MyPackage> {

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    protected BaseService<MyPackage> getService() {
        return packageService;
    }
}
