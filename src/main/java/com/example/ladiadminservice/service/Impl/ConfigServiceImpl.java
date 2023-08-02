package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.ConfigRepository;
import com.example.ladiadminservice.repository.entity.Config;
import com.example.ladiadminservice.service.ConfigService;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {

    private final ConfigRepository configRepository;

    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    protected BaseRepository<Config> getRepository() {
        return configRepository;
    }
}
