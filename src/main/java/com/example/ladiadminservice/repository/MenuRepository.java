package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Menu;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu> {

    List<Menu> getByIdInAndStatus(List<Long> ids, Integer status);
}
