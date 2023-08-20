package com.example.ladiadminservice.repository.entity;

import com.example.ladiadminservice.uitl.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "package")
public class MyPackage extends BaseEntity {
    private String code;
    private String name;
    private String image;
    private String description;
    private Integer type;
    private Long cost;
    private String note;
    private Integer priority;

    @Column(name = "shop_limit")
    private Integer shopLimit;

    @Column(name = "user_limit")
    private Integer userLimit;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime endDate;
}
