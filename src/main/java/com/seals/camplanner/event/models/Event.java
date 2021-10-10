package com.seals.camplanner.event.models;

import com.seals.camplanner.location.models.Location;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "private_event", nullable = false)
    private boolean privateEvent;

    @Column(name = "event_date", nullable = false)
    private Timestamp eventDate;

    @Column(name = "starts", nullable = false)
    private Timestamp starts;

    @Column(name = "ends", nullable = false)
    private Timestamp ends;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}
