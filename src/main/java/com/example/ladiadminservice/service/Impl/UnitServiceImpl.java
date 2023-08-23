package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.model.UnitInfoDto;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitRepository;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.DomainService;
import com.example.ladiadminservice.service.UnitPackageService;
import com.example.ladiadminservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitServiceImpl extends BaseServiceImpl<Unit> implements UnitService {

    @Autowired
    private UnitPackageService unitPackageService;
    @Autowired
    private DomainService domainService;

    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    protected BaseRepository<Unit> getRepository() {
        return unitRepository;
    }

    @Override
    public UnitInfoDto getUnitInfo(Long id) throws Exception {
        Unit unit = this.getById(id);
        DomainDto domain = domainService.getByUnitId(id);
        UnitInfoDto dto = UnitInfoDto.builder()
                .domain(domain)
                .unit(unit)
                .build();
        Optional<UnitPackage> unitPackageOpt = unitPackageService.getActiveByUnitId(id);
        if (unitPackageOpt.isEmpty()) return dto;

        UnitPackage unitPackage = unitPackageOpt.get();
        dto.setUnitPackageId(unitPackage.getId());
        dto.setStatus(unitPackage.getStatus());
        dto.setCost(unitPackage.getCost());
        dto.setMyPackage(unitPackage.getMyPackage());
        dto.setStartDate(dto.getStartDate());
        dto.setEndDate(dto.getEndDate());
        return dto;
    }
}
