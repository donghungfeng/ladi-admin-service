package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    protected BaseService<Menu> getService() {
        return menuService;
    }
}
