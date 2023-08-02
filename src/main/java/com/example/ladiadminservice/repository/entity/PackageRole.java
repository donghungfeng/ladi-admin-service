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
@Table(name = "package_role")
public class PackageRole extends BaseEntity {
    private Long packageId;
    private Long roleId;
}
