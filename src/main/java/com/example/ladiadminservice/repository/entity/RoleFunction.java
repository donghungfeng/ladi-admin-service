package com.example.ladiadminservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_function")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter@Builder
@Data
public class RoleFunction extends BaseEntity{
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "function_id", nullable = false)
    private Long functionId;


}
