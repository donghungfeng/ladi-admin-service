package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PackageRoleRepository;
import com.example.ladiadminservice.repository.entity.PackageRole;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.service.PackageRoleService;
import com.example.ladiadminservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageRoleServiceImpl extends BaseServiceImpl<PackageRole> implements PackageRoleService {

    private final PackageRoleRepository packageRoleRepository;
    @Autowired
    private RoleService roleService;

    public PackageRoleServiceImpl(PackageRoleRepository packageRoleRepository) {
        this.packageRoleRepository = packageRoleRepository;
    }

    @Override
    protected BaseRepository<PackageRole> getRepository() {
        return packageRoleRepository;
    }

    @Override
    public void deleteByPackageId(Long packageId) {
        List<PackageRole> packageRoles = this.getActiveByPackageId(packageId);
        packageRoles.forEach(e -> e.setStatus(Status.DELETED));
        packageRoleRepository.saveAll(packageRoles);
    }

    @Override
    public List<Role> getRolesByPackageId(Long packageId) {
        List<PackageRole> packageRoles = this.getActiveByPackageId(packageId);
        return roleService.getAllByInId(
                packageRoles.stream()
                        .map(PackageRole::getRoleId)
                        .collect(Collectors.toList())
        );
    }

    private List<PackageRole> getActiveByPackageId(Long packageId) {
        return packageRoleRepository.findByPackageIdAndStatus(packageId, Status.ACTIVE);
    }


}
