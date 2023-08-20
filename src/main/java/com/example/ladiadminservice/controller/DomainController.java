package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.repository.entity.Domain;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.DomainService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/domain")
public class DomainController extends BaseController<Domain> {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    protected BaseService<Domain> getService() {
        return domainService;
    }


}
