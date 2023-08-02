package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleMenuRepository;
import com.example.ladiadminservice.repository.entity.RoleMenu;
import com.example.ladiadminservice.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu> implements BaseService<RoleMenu> {

    private final RoleMenuRepository roleMenuRepository;

    public RoleMenuServiceImpl(RoleMenuRepository roleMenuRepository) {
        this.roleMenuRepository = roleMenuRepository;
    }

    @Override
    protected BaseRepository<RoleMenu> getRepository() {
        return roleMenuRepository;
    }
}
