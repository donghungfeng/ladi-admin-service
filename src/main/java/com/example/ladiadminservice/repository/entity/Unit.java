package com.example.ladiadminservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "unit")
@Data
public class Unit extends BaseEntity {
    private String code;
    private String name;
    private String logo;
    private String email;
    private String address;
    private String phone;
    private String note;

    @Column(name = "unit_rank")
    private Integer rank;
    private Integer discount;
    private Long amount;
}
