package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.AssignMenuPermissionReq;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.MenuPermissionService;
import com.example.ladiadminservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

    @Autowired
    private MenuPermissionService menuPermissionService;
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    protected BaseService<Menu> getService() {
        return menuService;
    }

    @PostMapping("/permission/assign")
    BaseResponse assignMenuPermission(@RequestBody AssignMenuPermissionReq req) throws Exception {
        menuService.assignPermissions(req);
        return new BaseResponse().success();
    }

    @GetMapping("/permission")
    BaseResponse getPermission(@RequestParam Long menuId) {
        return new BaseResponse().success(menuPermissionService.getPermissionsByMenuId(menuId));
    }
}
