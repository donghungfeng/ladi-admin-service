package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.PermissionRepository;
import com.example.ladiadminservice.repository.entity.Permission;
import com.example.ladiadminservice.service.PermissionService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected BaseRepository<Permission> getRepository() {
        return permissionRepository;
    }

    @Override
    public List<Permission> findByIds(List<Long> ids) {
        return permissionRepository.getByIdInAndStatus(ids, Status.ACTIVE);
    }

    @Override
    public Permission create(Permission req) throws Exception {
        validateDuplicateCode(req.getCode());
        return super.create(req);
    }

    @Override
    public Permission update(Permission req) throws Exception {
        Permission entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<Permission> entityOptional = permissionRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (entityOptional.isPresent())
            throw new Exception(String.format("Đơn vị có mã %s đã tồn tại!", code));
    }
}
