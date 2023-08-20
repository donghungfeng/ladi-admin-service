package com.example.ladiadminservice.mapper;

import org.modelmapper.ModelMapper;

public class BaseMapper {
    protected ModelMapper mapper;

    public BaseMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
    }
}
