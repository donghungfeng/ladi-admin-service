package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.PackageRole;
import com.example.ladiadminservice.repository.entity.Role;

import java.util.List;

public interface PackageRoleService extends BaseService<PackageRole> {

    void deleteByPackageId(Long packageId);

    List<Role> getRolesByPackageId(Long packageId);
}
