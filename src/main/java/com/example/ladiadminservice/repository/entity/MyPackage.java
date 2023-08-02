package com.example.ladiadminservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

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
}
