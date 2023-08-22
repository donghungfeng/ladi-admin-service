package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.mapper.PackageMapper;
import com.example.ladiadminservice.model.PackageDto;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitPackageRepository;
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
    private final PackageMapper packageMapper;

    public UnitPackageServiceImpl(UnitPackageRepository packageRepository, PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.packageMapper = packageMapper;
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

    @Override
    public PackageDto getPackageDtoByUnitId(Long unitId) {
        Optional<UnitPackage> unitPackageOptional = this.getActiveByUnitId(unitId);
        return unitPackageOptional.isPresent()
                ? packageMapper.toDto(unitPackageOptional.get().getMyPackage())
                : null;
    }
}
