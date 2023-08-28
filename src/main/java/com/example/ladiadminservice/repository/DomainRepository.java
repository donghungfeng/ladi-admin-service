package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Domain;

import java.util.List;
import java.util.Optional;

public interface DomainRepository extends BaseRepository<Domain> {

    List<Domain> getByUnit_IdAndStatus(Long unitId, Integer status);

    Optional<Domain> getByUrlAndStatusGreaterThan(String url, Integer status);
}
