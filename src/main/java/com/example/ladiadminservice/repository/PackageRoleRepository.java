package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.PackageRole;

import java.util.List;

public interface PackageRoleRepository extends BaseRepository<PackageRole> {
    List<PackageRole> findByPackageIdAndStatus(Long packageId, Integer status);
}
