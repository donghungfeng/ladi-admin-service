package com.example.ladiadminservice.service;

import com.example.ladiadminservice.repository.entity.Function;

import java.util.List;

public interface FunctionService extends BaseService<Function> {
    List<Function> getAllByInId(List<Long> idList);
}
