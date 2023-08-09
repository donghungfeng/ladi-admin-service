package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.DomainRepository;
import com.example.ladiadminservice.repository.entity.Domain;
import com.example.ladiadminservice.service.DomainService;
import org.springframework.stereotype.Service;

@Service
public class DomainServiceImpl extends BaseServiceImpl<Domain> implements DomainService {

    private final DomainRepository domainRepository;

    public DomainServiceImpl(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    protected BaseRepository<Domain> getRepository() {
        return domainRepository;
    }
}
