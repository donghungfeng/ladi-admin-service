package com.example.ladiadminservice.model.req;

import lombok.Data;

import java.util.List;

@Data
public class AddUserRoleReq {

    private Long userId;
    private List<Long> roleIds;
}