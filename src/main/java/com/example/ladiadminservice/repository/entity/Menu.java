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
@Table(name = "menu")
public class Menu extends BaseEntity {
    private String code;
    private String name;
    private String url;
    private String description;
    private String icon;
    private String note;
    private Integer priority;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;
}
