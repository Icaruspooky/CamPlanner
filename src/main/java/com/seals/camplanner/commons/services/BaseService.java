package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.models.BaseEntity;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines the methods that Generic Services should implement.
 *
 * @param <T> The Entity Type that this Service manages.
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be null.
     * @return the entity with the given id.
     * @throws NoSuchElementException if no entity found with id.
     * @throws IllegalArgumentException if id is null
     */
    T findById(Long id);

    /**
     * Returns all instances managed by the service. Be aware of possible memory issues.
     *
     * @return list of entities
     */
    List<T> findAll();

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have
     * changed the entity instance completely.
     *
     * @param entity must not be null.
     * @return the saved entity, will never be null.
     * @throws IllegalArgumentException if the given entity is null
     */
    T save(T entity);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be null.
     * @throws IllegalArgumentException if id is null
     */
    void deleteById(Long id);

    /**
     * Deletes all entities managed by the service.
     */
    void deleteAll();
}
