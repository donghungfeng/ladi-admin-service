package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PackageRoleRepository;
import com.example.ladiadminservice.repository.entity.PackageRole;
import com.example.ladiadminservice.service.PackageRoleService;
import org.springframework.stereotype.Service;

@Service
public class PackageRoleServiceImpl extends BaseServiceImpl<PackageRole> implements PackageRoleService {

    private final PackageRoleRepository packageRoleRepository;

    public PackageRoleServiceImpl(PackageRoleRepository packageRoleRepository) {
        this.packageRoleRepository = packageRoleRepository;
    }

    @Override
    protected BaseRepository<PackageRole> getRepository() {
        return packageRoleRepository;
    }
}
