package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    public List<T> search(String filter);
    public T create(T t);
    public T update(T t);
    public T getById(Long id);
    public List<T> getAll();
    public String delete(Long id);
}
