package com.example.ladiadminservice.service.Impl;

import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.repository.entity.BaseEntity;
import com.example.ladiadminservice.query.CustomRsqlVisitor;
import com.example.ladiadminservice.repository.BaseRepository;
import com.example.ladiadminservice.service.BaseService;
import com.example.ladiadminservice.uitl.ObjectMapperUtils;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected abstract BaseRepository<T> getRepository();

    @Override
    public List<T> getAll() {
        return this.getRepository().findAll();
    }

    @Override
    public List<T> search(String filter) {
        Node rootNode = new RSQLParser().parse(filter);
        Specification<T> spec = rootNode.accept(new CustomRsqlVisitor<T>());
        return this.getRepository().findAll(spec);
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
    public void saveAll(List<T> entities) {
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
