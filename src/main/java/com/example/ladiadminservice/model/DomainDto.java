package com.example.ladiadminservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.ZonedDateTime;

@Data
public class DomainDto {
    private String url;
    private String name;
    private String server;
    private Long cost;
    private String note;

    private Long id;

    private Integer status;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private ZonedDateTime createDate;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private ZonedDateTime updateDate;

    private String createdBy;

    private String updatedBy;
}
