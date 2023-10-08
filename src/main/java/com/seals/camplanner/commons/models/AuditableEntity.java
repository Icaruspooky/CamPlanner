package com.seals.camplanner.commons.models;

import java.sql.Timestamp;

import com.seals.camplanner.commons.services.BaseServiceImpl;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO This needs user context before it can be fully implemented with.
 * {@link BaseServiceImpl}
 *
 * <p>Basic Auditable Entity, including date and user information for when the record was created and last modified.</p>
 *
 * <p>Other entities can just extend this to have standard auditing columns.</p>
 *
 * <p>If using lombok @{@link Data} or @{@link lombok.EqualsAndHashCode} don't forget to include the
 * 'callSuper = true' parameter in order for the equals and hashcode to consider inherited fields.</p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity {

    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    private Timestamp createdDate;

    @Column(name = "last_modified_by", nullable = false)
    private Long lastModifiedBy;

    @Column(name = "last_modified_date", nullable = false)
    private Timestamp lastModifiedDate;
}
