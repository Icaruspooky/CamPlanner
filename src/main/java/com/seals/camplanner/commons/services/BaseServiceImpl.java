package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.exceptions.NotFoundException;
import com.seals.camplanner.commons.models.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@link BaseService} implemetation.
 */
@Slf4j
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private static final String NOT_FOUND_MESSAGE = "Unable to find %s with supplied id (%s)";

    protected abstract JpaRepository<T, Long> getRepository();

    public abstract String getEntityName();

    /**
     * {@inheritDoc}
     */
    @Override
    public T findById(Long id) {
        return this.getRepository()
                   .findById(id)
                   .orElseThrow(() -> new NotFoundException(this.getNotFoundMessage(id)));
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
        try {
            this.getRepository().deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(this.getNotFoundMessage(id));
        }
        log.info(String.format("Deleted %s of id: %s", this.getEntityName(), id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        this.getRepository().deleteAll();
        log.info(String.format("All instances of %s were deleted", this.getEntityName()));
    }

    public String getNotFoundMessage(Long id) {
        return String.format(NOT_FOUND_MESSAGE, this.getEntityName(), id);
    }
}
