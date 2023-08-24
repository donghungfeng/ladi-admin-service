package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Unit;

import java.util.Optional;

public interface UnitRepository extends BaseRepository<Unit> {

    Optional<Unit> getByCodeAndStatusGreaterThan(String code, Integer status);
}
