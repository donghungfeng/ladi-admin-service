package com.example.ladiadminservice.controller;

import com.example.ladiadminservice.model.BaseEntity;
import com.example.ladiadminservice.response.BaseResponse;
import com.example.ladiadminservice.service.BaseService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
public abstract class BaseController<T extends BaseEntity> {
    protected abstract BaseService<T> getService();

    @PostMapping("")
    public BaseResponse create(@RequestBody T t) throws NoSuchAlgorithmException {
        return new BaseResponse(200, "Tạo thành công!", this.getService().create(t));
    }

    @GetMapping("search")
    public BaseResponse search(@RequestParam(value = "search") String search) {
        return new BaseResponse(200, "Lấy dữ liệu thành công!", this.getService().search(search));
    }

    @PutMapping("")
    public BaseResponse update(@RequestBody T t) throws NoSuchAlgorithmException {
        return new BaseResponse(200, "Cập nhật thành công!", this.getService().update(t));
    }


    @DeleteMapping("")
    public BaseResponse deleteById(@RequestParam(name = "id") Long id){
        return new BaseResponse(200, "OK", this.getService().delete(id));
    }
}
