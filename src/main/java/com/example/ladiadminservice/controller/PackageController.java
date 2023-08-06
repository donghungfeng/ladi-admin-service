package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.AssignPackageRoleReq;
import com.example.ladiadminservice.model.req.AssignUserRoleReq;
import com.example.ladiadminservice.repository.entity.MyPackage;
import com.example.ladiadminservice.service.BaseService;

import com.example.ladiadminservice.service.PackageRoleService;
import com.example.ladiadminservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
public class PackageController extends BaseController<MyPackage> {

    @Autowired
    private PackageRoleService packageRoleService;
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    protected BaseService<MyPackage> getService() {
        return packageService;
    }

    @PostMapping("/role/assign")
    public BaseResponse assignPackageRole(@RequestBody AssignPackageRoleReq req) {
        packageService.assignRole(req);
        return new BaseResponse().success();
    }

    @GetMapping("/role")
    public BaseResponse getRoleOfUser(@RequestParam Long userId) {
        return new BaseResponse().success(packageRoleService.getRolesByPackageId(userId));
    }
}
