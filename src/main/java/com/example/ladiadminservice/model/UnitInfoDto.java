package com.example.ladiadminservice.model;

import com.example.ladiadminservice.repository.entity.MyPackage;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.uitl.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnitInfoDto {
    private Long unitPackageId;
    private Integer status;

    private MyPackage myPackage;

    private Unit unit;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime startDate;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime endDate;

    private Long cost;
    private DomainDto domain;
}
