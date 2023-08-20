package com.example.ladiadminservice.mapper;

import com.example.ladiadminservice.model.DomainDto;
import com.example.ladiadminservice.repository.entity.Domain;
import org.springframework.stereotype.Component;

@Component
public class DomainMapper extends BaseMapper {

    public DomainDto toDto(Domain domain) {
        return mapper.map(domain, DomainDto.class);
    }
}
