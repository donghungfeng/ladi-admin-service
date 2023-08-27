package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Config;

import java.util.Optional;

public interface ConfigRepository extends BaseRepository<Config> {

    Optional<Config> getByCodeAndStatusGreaterThan(String code, Integer status);
}
