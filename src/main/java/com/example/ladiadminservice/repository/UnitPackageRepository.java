package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.UnitPackage;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface UnitPackageRepository extends BaseRepository<UnitPackage> {

    @Query("SELECT up FROM UnitPackage up " +
            " WHERE up.status > 0 " +
            " AND up.unit.id = :unitId " +
            " AND up.startDate <= :now " +
            " AND up.endDate >= :now ")
    List<UnitPackage> getActiveByUnit(Long unitId, ZonedDateTime now);

    List<UnitPackage> getByUnitAndStatusGreaterThan(Unit unit, Integer status);
}
