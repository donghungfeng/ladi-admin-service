package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.UnitInfoDto;
import com.example.ladiadminservice.repository.entity.Unit;

public interface UnitService extends BaseService<Unit> {

    UnitInfoDto getUnitInfo(Long id) throws Exception;
}
