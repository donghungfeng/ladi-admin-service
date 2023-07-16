package com.example.ladiadminservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "functions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Function extends BaseEntity{
    private String url;

}
