package com.example.ladiadminservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "user_role")
public class UserRole extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
