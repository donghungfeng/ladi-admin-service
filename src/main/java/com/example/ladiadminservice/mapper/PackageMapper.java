package com.example.ladiadminservice.mapper;

import com.example.ladiadminservice.model.PackageDto;
import com.example.ladiadminservice.repository.entity.MyPackage;
import org.springframework.stereotype.Component;

@Component
public class PackageMapper extends BaseMapper {

    public PackageDto toDto(MyPackage myPackage) {
        if (myPackage == null) return null;
        return mapper.map(myPackage, PackageDto.class);
    }
}
