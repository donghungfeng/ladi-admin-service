package com.example.ladiadminservice.model;

import com.example.ladiadminservice.repository.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userName;

    private String email;

    private String address;

    private Unit unit;
    private String fullName;
    
}
