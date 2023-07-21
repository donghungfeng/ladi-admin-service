package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.model.Function;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.FunctionRepository;
import com.example.ladiadminservice.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements FunctionService {
    @Autowired
    FunctionRepository functionRepository;
    @Override
    protected BaseRepository<Function> getRepository() {
        return functionRepository;
    }

    @Override
    public List<Function> getAllByInId(List<Long> idList) {
        return functionRepository.findAllByInId(idList);
    }
}
