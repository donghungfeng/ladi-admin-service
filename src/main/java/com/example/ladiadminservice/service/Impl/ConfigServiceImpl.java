package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.ConfigRepository;
import com.example.ladiadminservice.repository.entity.Config;
import com.example.ladiadminservice.service.ConfigService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Config create(Config req) throws Exception {
        validateDuplicateCode(req.getCode());
        return super.create(req);
    }

    @Override
    public Config update(Config req) throws Exception {
        Config entityMy = this.getById(req.getId());

        if (!StringUtils.isEmpty(req.getCode()) && !Objects.equals(entityMy.getCode(), req.getCode()))
            validateDuplicateCode(req.getCode());

        ObjectMapperUtils.map(req, entityMy);
        return getRepository().save(entityMy);
    }

    private void validateDuplicateCode(String code) throws Exception {
        Optional<Config> entityOptional = configRepository.getByCodeAndStatusGreaterThan(code, Status.DELETED);
        if (entityOptional.isPresent())
            throw new Exception(String.format("Dữ liệu có mã %s đã tồn tại!", code));
    }
}
