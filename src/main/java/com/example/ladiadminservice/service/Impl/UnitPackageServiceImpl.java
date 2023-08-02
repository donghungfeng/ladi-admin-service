package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.UnitPackageRepository;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import com.example.ladiadminservice.service.UnitPackageService;
import org.springframework.stereotype.Service;

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
}
