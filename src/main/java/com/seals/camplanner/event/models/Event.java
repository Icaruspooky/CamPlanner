package com.seals.camplanner.event.models;

import com.seals.camplanner.commons.models.BaseEntity;
import com.seals.camplanner.location.models.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Event extends BaseEntity {

    @Column(name = "private_event", nullable = false)
    private boolean privateEvent;

    @Column(name = "event_date", nullable = false)
    private Timestamp eventDate;

    @Column(name = "starts", nullable = false)
    private Timestamp starts;

    @Column(name = "ends", nullable = false)
    private Timestamp ends;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}

