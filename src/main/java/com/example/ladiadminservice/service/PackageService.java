package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.req.AssignPackageRoleReq;
import com.example.ladiadminservice.repository.entity.MyPackage;

public interface PackageService extends BaseService<MyPackage> {

    void assignRole(AssignPackageRoleReq req) throws Exception;
}
