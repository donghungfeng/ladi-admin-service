package com.example.ladiadminservice.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter@Builder
@Data
public class User extends BaseEntity{
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "unit_id") // thông qua khóa ngoại address_id
    private Unit unit ;


}
