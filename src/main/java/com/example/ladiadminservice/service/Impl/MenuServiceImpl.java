package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.MenuRepository;
import com.example.ladiadminservice.repository.entity.Menu;
import com.example.ladiadminservice.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    protected BaseRepository<Menu> getRepository() {
        return menuRepository;
    }
}
