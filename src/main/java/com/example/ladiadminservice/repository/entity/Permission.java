package com.example.ladiadminservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
    private String name;
    private String code;
    @Column(name = "permission_group")
    private Integer group;
}
