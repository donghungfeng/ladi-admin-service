package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    List<T> search(String filter);

    T create(T t);

    T update(T t);

    T getById(Long id);

    List<T> getByIds(List<Long> ids);

    List<T> getAll();

    void delete(Long id);

    void saveAll(List<T> entities);
}
