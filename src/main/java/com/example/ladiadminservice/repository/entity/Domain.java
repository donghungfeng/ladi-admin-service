package com.example.ladiadminservice.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "domain")
public class Domain extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    private String url;
    private String name;
    private String server;
    private Long cost;
    private String note;
}
