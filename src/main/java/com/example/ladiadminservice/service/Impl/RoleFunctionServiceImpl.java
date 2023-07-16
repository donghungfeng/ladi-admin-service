package com.example.ladiadminservice.service.Impl;



import com.example.ladiadminservice.model.RoleFunction;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.repository.RoleFunctionRepository;
import com.example.ladiadminservice.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFunctionServiceImpl extends BaseServiceImpl<RoleFunction> implements RoleFunctionService {
    @Autowired
    RoleFunctionRepository roleFunctionRepository;
    @Override
    protected BaseRepository<RoleFunction> getRepository() {
        return roleFunctionRepository;
    }
}
