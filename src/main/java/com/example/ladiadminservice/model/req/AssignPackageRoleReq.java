package com.example.ladiadminservice.model.req;

import lombok.Data;

import java.util.List;

@Data
public class AssignPackageRoleReq {

    private Long packageId;
    private List<Long> roleIds;
}
