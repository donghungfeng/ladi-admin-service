package com.example.ladiadminservice.service;

import com.example.ladiadminservice.model.UnitDetail;
import com.example.ladiadminservice.model.req.SearchReq;
import com.example.ladiadminservice.repository.entity.Unit;
import org.springframework.data.domain.Page;

public interface UnitService extends BaseService<Unit> {

    UnitDetail getUnitDetail(Long id) throws Exception;

    Page<UnitDetail> searchUnitDetail(SearchReq req);
}
