package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.req.AssignMenuPermissionReq;
import com.example.ladiadminservice.repository.entity.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu> {
    List<Menu> findByIds(List<Long> ids);

    void assignPermissions(AssignMenuPermissionReq req) throws Exception;
}
