package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.mapper.DomainMapper;
import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.DomainRepository;
import com.example.ladiadminservice.repository.entity.Domain;
import com.example.ladiadminservice.service.DomainService;
import com.example.ladiadminservice.service.UnitService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DomainServiceImpl extends BaseServiceImpl<Domain> implements DomainService {

    @Autowired
    private UnitService unitService;
    private final DomainRepository domainRepository;
    private final DomainMapper mapper;

    public DomainServiceImpl(DomainRepository domainRepository, DomainMapper mapper) {
        this.domainRepository = domainRepository;
        this.mapper = mapper;
    }

    @Override
    protected BaseRepository<Domain> getRepository() {
        return domainRepository;
    }

    @Override
    public DomainDto getByUnitId(Long unitId) {
        List<Domain> domains = domainRepository.getByUnit_IdAndStatus(unitId, Status.ACTIVE);
        if (CollectionUtils.isEmpty(domains)) return null;
        return mapper.toDto(domains.get(0));
    }

    @Override
    public Domain update(Domain req) throws Exception {
        Domain entityMy = this.getById(req.getId());
        if (req.getUnit() != null) req.setUnit(unitService.getById(req.getUnit().getId()));
        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }
}
