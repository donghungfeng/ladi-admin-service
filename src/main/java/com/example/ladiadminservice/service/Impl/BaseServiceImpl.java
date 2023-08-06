package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.SearchReq;
import com.example.ladiadminservice.repository.entity.BaseEntity;
import com.example.ladiadminservice.query.CustomRsqlVisitor;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private static final String DELETED_FILTER = ";status>-1";

    protected abstract BaseRepository<T> getRepository();

    @Override
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

    @Override
    public Page<T> search(SearchReq req) {
        req.setFilter(req.getFilter().concat(DELETED_FILTER));
        Node rootNode = new RSQLParser().parse(req.getFilter());
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        Pageable pageable = getPage(req);
        return this.getRepository().findAll(spec, pageable);
    }

    protected Pageable getPage(SearchReq req) {
        String[] sortList = req.getSort().split(",");
        Sort.Direction direction = sortList[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return req.getSize() != null
                ?
                PageRequest.of(req.getPage(), req.getSize(), direction, sortList[0])
                :
                Pageable.unpaged();
    }

    @Override
    public T create(T t) {
        t.setStatus(Status.ACTIVE);
        return this.getRepository().save(t);
    }

    @Override
    public T update(T t) {
        T entityMy = this.getById(t.getId());
        ObjectMapperUtils.map(t, entityMy);
        return getRepository().save(entityMy);
    }

    @Override
    public T getById(Long id) {
        return this.getRepository().findAllById(id);
    }

    @Override
    public void delete(Long id) {
        T t = this.getRepository().findAllById(id);
        t.setStatus(Status.DELETED);
        this.getRepository().save(t);
    }

    @Override
    public void createAll(List<T> entities) {
        entities.forEach(e -> {
            if (e.getStatus() == null) e.setStatus(Status.ACTIVE);
        });
        this.getRepository().saveAll(entities);
    }

    @Override
    public List<T> getByIds(List<Long> ids) {
        Iterable<T> eIterable = this.getRepository().findAllById(ids);
        List<T> target = new ArrayList<>();
        eIterable.forEach(target::add);
        return target;
    }
}
