package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.model.UnitInfoDto;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitRepository;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.DomainService;
import com.example.ladiadminservice.service.UnitPackageService;
import com.example.ladiadminservice.service.UnitService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
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
    public Unit create(Unit req) throws Exception {
        validateDuplicateCode(req.getCode());
        return super.create(req);
    }

    @Override
    public Unit update(Unit req) throws Exception {
        Unit entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<Unit> unitOptional = unitRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (unitOptional.isPresent())
            throw new Exception(String.format("Đơn vị có mã %s đã tồn tại!", code));
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
