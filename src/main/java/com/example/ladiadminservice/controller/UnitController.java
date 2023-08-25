package com.example.ladiadminservice.controller;


import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.req.SearchReq;
import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.service.UnitService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("unit")
public class UnitController extends BaseController<Unit> {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    protected BaseService<Unit> getService() {
        return unitService;
    }

    @GetMapping("/info")
    BaseResponse getUnitInfo(@RequestParam Long id) throws Exception {
        return new BaseResponse().success(unitService.getUnitDetail(id));
    }

    @GetMapping("/search/detail")
    BaseResponse searchDetail(SearchReq req) {
        return new BaseResponse().success(unitService.searchUnitDetail(req));
    }
}
