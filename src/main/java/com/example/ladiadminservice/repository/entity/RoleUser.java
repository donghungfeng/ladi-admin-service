package com.example.ladiadminservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter@Builder
@Data
public class RoleUser extends BaseEntity{
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;


}
