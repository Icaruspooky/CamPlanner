package com.seals.camplanner.commons.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * Basic Entity with a Long id column.
 *
 * <p>Other entities (with Long id) can just extend this to have a standard id column</p>
 *
 * <p>If using lombok @{@link Data} or @{@link lombok.EqualsAndHashCode} don't forget to include the
 * 'callSuper = true' parameter in order for the equals and hashcode to consider the id field. Example:
 * {@link AuditableEntity} </p>
 */
@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
}
