package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.model.Unit;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitRepository;
import com.example.ladiadminservice.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl extends BaseServiceImpl<Unit> implements UnitService {
    @Autowired
    UnitRepository unitRepository;
    @Override
    protected BaseRepository<Unit> getRepository() {
        return unitRepository;
    }
}
