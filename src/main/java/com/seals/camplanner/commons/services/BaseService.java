package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.models.BaseEntity;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

    protected abstract JpaRepository<T, Long> getRepository();

    public abstract String getEntityName();

    @Override
    public T findById(Long id) {
        return this.getRepository()
                   .findById(id)
                   .orElseThrow(() -> new NoSuchElementException(
                           String.format("Unable to find %s with supplied id", this.getEntityName())
                   ));
    }

    @Override
    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    @Override
    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.getRepository().deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.getRepository().deleteAll();
    }
}
