package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> getByIdInAndStatus(List<Long> ids, Integer status);

    Optional<Menu> getByCodeAndStatusNotLike(String code, Integer status);
}
