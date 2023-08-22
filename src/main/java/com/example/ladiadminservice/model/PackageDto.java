package com.example.ladiadminservice.model;

import com.example.ladiadminservice.uitl.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PackageDto {

    private String code;
    private String name;

    private String description;
    private Integer type;
    private Long cost;
    private String note;
    private Integer priority;

    private Integer shopLimit;

    private Integer userLimit;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime startDate;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime endDate;
}
