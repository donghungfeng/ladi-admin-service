package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.model.RoleFunction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleFunctionRepository extends BaseRepository<RoleFunction>{
    List<RoleFunction> findAllByRoleId(Long roleId);
    @Query(value = "SELECT * from role_function where role_id in :roleIdList", nativeQuery = true)
    List<RoleFunction> findAllByInRoleId(List<Long> roleIdList);
}
