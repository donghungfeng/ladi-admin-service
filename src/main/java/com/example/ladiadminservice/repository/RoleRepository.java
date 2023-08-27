package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    List<Role> findByIdInAndStatus(List<Long> idList, Integer status);

    Optional<Role> getByCodeAndStatusGreaterThan(String code, Integer status);

}
