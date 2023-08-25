package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.AssignPackageRoleReq;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PackageRepository;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.repository.entity.MyPackage;
import com.example.ladiadminservice.repository.entity.PackageRole;
import com.example.ladiadminservice.repository.entity.Role;
import com.example.ladiadminservice.service.PackageRoleService;
import com.example.ladiadminservice.service.PackageService;
import com.example.ladiadminservice.service.RoleService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageServiceImpl extends BaseServiceImpl<MyPackage> implements PackageService {

    @Autowired
    private PackageRoleService packageRoleService;
    @Autowired
    private RoleService roleService;

    private final PackageRepository packageRepository;

    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    protected BaseRepository<MyPackage> getRepository() {
        return packageRepository;
    }

    @Override
    public void assignRole(AssignPackageRoleReq req) throws Exception {
        MyPackage myPackage = this.getById(req.getPackageId());
        packageRoleService.deleteByPackageId(req.getPackageId());
        if (CollectionUtils.isEmpty(req.getRoleIds())) return;

        List<Role> roleList = roleService.getAllByInId(req.getRoleIds());
        if (CollectionUtils.isEmpty(roleList)) return;

        List<PackageRole> packageRoles = roleList.stream()
                .map(e -> PackageRole.builder()
                        .roleId(e.getId())
                        .packageId(myPackage.getId())
                        .build())
                .collect(Collectors.toList());
        packageRoleService.createAll(packageRoles);
    }

    @Override
    public MyPackage create(MyPackage req) throws Exception {
        validateDuplicateCode(req.getCode());
        return super.create(req);
    }

    @Override
    public MyPackage update(MyPackage req) throws Exception {
        MyPackage entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<MyPackage> entityOptional = packageRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (entityOptional.isPresent())
            throw new Exception(String.format("Dữ liệu có mã %s đã tồn tại!", code));
    }
}
