package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.mapper.PackageMapper;
import com.example.ladiadminservice.model.PackageDto;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitPackageRepository;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.PackageService;
import com.example.ladiadminservice.service.UnitPackageService;
import com.example.ladiadminservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UnitPackageServiceImpl extends BaseServiceImpl<UnitPackage> implements UnitPackageService {
    @Autowired
    private PackageService packageService;
    @Autowired
    private UnitService unitService;
    private final UnitPackageRepository unitPackageRepository;
    private final PackageMapper packageMapper;

    public UnitPackageServiceImpl(UnitPackageRepository unitPackageRepository, PackageMapper packageMapper) {
        this.unitPackageRepository = unitPackageRepository;
        this.packageMapper = packageMapper;
    }

    @Override
    protected BaseRepository<UnitPackage> getRepository() {
        return unitPackageRepository;
    }

    @Override
    public Optional<UnitPackage> getActiveByUnitId(Long unitId) {
        List<UnitPackage> unitPackageList = unitPackageRepository.getActiveByUnit(unitId, ZonedDateTime.now());
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

    @Override
    public UnitPackage create(UnitPackage unitPackage) throws Exception {
        unitPackage.setMyPackage(packageService.getById(unitPackage.getMyPackage().getId()));
        Unit unit = unitService.getById(unitPackage.getUnit().getId());
        unitPackage.setUnit(unit);

        this.deleteByUnit(unit);

        return super.create(unitPackage);
    }

    private void deleteByUnit(Unit unit) {
        List<UnitPackage> unitPackageList = unitPackageRepository.getByUnitAndStatusGreaterThan(unit, Status.DELETED);
        unitPackageList.forEach(e -> e.setStatus(Status.DELETED));
        unitPackageRepository.saveAll(unitPackageList);
    }
}
