package com.seals.camplanner.commons.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class with audit information.
 * TODO This needs user context before it can be implemented.
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
