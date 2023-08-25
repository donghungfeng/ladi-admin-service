package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.MyPackage;

import java.util.Optional;

public interface PackageRepository extends BaseRepository<MyPackage> {

    Optional<MyPackage> getByCodeAndStatusGreaterThan(String code, Integer status);
}
