package com.seals.camplanner.commons.services;

import com.seals.camplanner.commons.models.BaseEntity;
import java.util.List;

public interface IBaseService<T extends BaseEntity> {

    T findById(Long id);

    List<T> findAll();

    T save(T entity);

    void deleteById(Long id);

    void deleteAll();

}
