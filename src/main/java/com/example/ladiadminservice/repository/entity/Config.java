package com.example.ladiadminservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "config")
public class Config extends BaseEntity {
    private String code;
    private String name;
    private String value;
    private String description;
    private String defaultValue;
}
