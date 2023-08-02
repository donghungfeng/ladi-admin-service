package com.example.ladiadminservice.repository.entity;

import com.example.ladiadminservice.uitl.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "unit_package")
public class UnitPackage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "package_id")
    private MyPackage myPackage;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime endDate;

    private Long cost;
}
