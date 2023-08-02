package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PackageRepository;
import com.example.ladiadminservice.repository.entity.MyPackage;
import com.example.ladiadminservice.service.PackageService;
import org.springframework.stereotype.Service;

@Service
public class PackageServiceImpl extends BaseServiceImpl<MyPackage> implements PackageService {

    private final PackageRepository packageRepository;

    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    protected BaseRepository<MyPackage> getRepository() {
        return packageRepository;
    }
}
