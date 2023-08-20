package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitPackageRepository;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.UnitPackageService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnitPackageServiceImpl extends BaseServiceImpl<UnitPackage> implements UnitPackageService {

    private final UnitPackageRepository packageRepository;

    public UnitPackageServiceImpl(UnitPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    protected BaseRepository<UnitPackage> getRepository() {
        return packageRepository;
    }

    @Override
    public Optional<UnitPackage> getActiveByUnitId(Long unitId) {
        List<UnitPackage> unitPackageList = packageRepository.getActiveByUnit(unitId, ZonedDateTime.now());
        if (CollectionUtils.isEmpty(unitPackageList)) return Optional.empty();

        return Optional.of(unitPackageList.get(0));
    }
}
