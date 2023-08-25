package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.mapper.PackageMapper;
import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.model.UnitDetail;
import com.example.ladiadminservice.model.req.SearchReq;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitRepository;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.DomainService;
import com.example.ladiadminservice.service.UnitPackageService;
import com.example.ladiadminservice.service.UnitService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl extends BaseServiceImpl<Unit> implements UnitService {

    @Autowired
    private UnitPackageService unitPackageService;
    @Autowired
    private DomainService domainService;
    @Autowired
    private PackageMapper packageMapper;

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
    public UnitDetail getUnitDetail(Long id) throws Exception {
        Unit unit = this.getById(id);
        DomainDto domain = domainService.getByUnitId(id);
        UnitDetail dto = UnitDetail.builder()
                .domain(domain)
                .unit(unit)
                .build();
        Optional<UnitPackage> unitPackageOpt = unitPackageService.getActiveByUnitId(id);
        if (unitPackageOpt.isEmpty()) return dto;

        UnitPackage unitPackage = unitPackageOpt.get();

        mapPackageData(unitPackage, dto);

        return dto;
    }

    private void mapPackageData(UnitPackage unitPackage, UnitDetail dto) {
        dto.setUnitPackageId(unitPackage.getId());
        dto.setUnitPackageStatus(unitPackage.getStatus());
        dto.setCost(unitPackage.getCost());
        dto.setMyPackage(packageMapper.toDto(unitPackage.getMyPackage()));
        dto.setStartDate(unitPackage.getStartDate());
        dto.setEndDate(unitPackage.getEndDate());
    }

    @Override
    public Page<UnitDetail> searchUnitDetail(SearchReq req) {
        Page<Unit> unitPage = super.search(req);
        List<Unit> units = unitPage.getContent();
        List<UnitPackage> unitPackageList = unitPackageService.getByUnits(units);
        return unitPage.map(e -> this.convert(
                e
                , this.getUnitPackagesByUnit(e, unitPackageList)
        ));
    }

    private List<UnitPackage> getUnitPackagesByUnit(Unit unit, List<UnitPackage> unitPackageList) {
        return unitPackageList.stream()
                .filter(e -> Objects.equals(e.getUnit().getId(), unit.getId()))
                .collect(Collectors.toList());
    }

    private UnitDetail convert(Unit unit, List<UnitPackage> unitPackageList) {
        UnitDetail unitDetail = UnitDetail.builder()
                .unit(unit)
                .build();
        if (CollectionUtils.isEmpty(unitPackageList))
            return unitDetail;

        UnitPackage unitPackage = this.getUnitPackage(unitPackageList);
        mapPackageData(unitPackage, unitDetail);
        return unitDetail;
    }

    private UnitPackage getUnitPackage(List<UnitPackage> unitPackageList) {
        Optional<UnitPackage> unitPackageActiveOptional = this.getActive(unitPackageList);
        return unitPackageActiveOptional.isPresent()
                ? unitPackageActiveOptional.get()
                : unitPackageList.get(0);
    }

    private Optional<UnitPackage> getActive(List<UnitPackage> unitPackageList) {
        return unitPackageList.stream()
                .filter(e -> Objects.equals(e.getStatus(), Status.ACTIVE))
                .findFirst();
    }
}
