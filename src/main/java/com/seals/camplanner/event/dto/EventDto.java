package com.seals.camplanner.event.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDto {

    private Long id;
    private boolean privateEvent;
    private Timestamp eventDate;
    private Timestamp starts;
    private Timestamp ends;
    private Long locationId;
}
