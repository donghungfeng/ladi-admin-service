package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    List<Role> findByIdInAndStatus(List<Long> idList, Integer status);
}
