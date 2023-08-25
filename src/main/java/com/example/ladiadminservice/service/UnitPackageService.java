package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.PackageDto;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;

import java.util.List;
import java.util.Optional;

public interface UnitPackageService extends BaseService<UnitPackage> {

    Optional<UnitPackage> getActiveByUnitId(Long unitId);

    PackageDto getPackageDtoByUnitId(Long unitId);

    List<UnitPackage> getByUnits(List<Unit> units);
}
