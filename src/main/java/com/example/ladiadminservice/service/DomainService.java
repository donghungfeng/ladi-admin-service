package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.repository.entity.Domain;

public interface DomainService extends BaseService<Domain> {

    DomainDto getByUnitId(Long unitId);
}
