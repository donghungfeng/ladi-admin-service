package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.req.SearchReq;
import com.example.ladiadminservice.repository.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    Page<T> search(SearchReq req);

    T create(T t);

    T update(T t);

    T getById(Long id);

    List<T> getByIds(List<Long> ids);

    List<T> getAll();

    void delete(Long id);

    void saveAll(List<T> entities);
}
