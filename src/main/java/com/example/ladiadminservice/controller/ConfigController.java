package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Config;

import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.ConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController<Config> {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    protected BaseService<Config> getService() {
        return configService;
    }
}
