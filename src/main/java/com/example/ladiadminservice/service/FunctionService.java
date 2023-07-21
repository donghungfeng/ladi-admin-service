package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.Function;
import com.example.ladiadminservice.model.Role;

import java.util.List;

public interface FunctionService extends BaseService<Function> {
    List<Function> getAllByInId(List<Long> idList);
}
