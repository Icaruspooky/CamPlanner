package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.models.BaseEntity;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link BaseService} implemetation.
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract JpaRepository<T, Long> getRepository();

    public abstract String getEntityName();

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Long id) {
        return this.getRepository()
                   .findById(id)
                   .orElseThrow(() -> new NoSuchElementException(
                           String.format("Unable to find %s with supplied id", this.getEntityName())
                   ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        this.getRepository().deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        this.getRepository().deleteAll();
    }
}
