package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Function;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionRepository extends BaseRepository<Function>{
    @Query(value = "SELECT * from functions where id in :idList", nativeQuery = true)
    List<Function> findAllByInId(List<Long> idList);
}
