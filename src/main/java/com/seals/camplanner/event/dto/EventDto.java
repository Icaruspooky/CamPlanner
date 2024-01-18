package com.seals.camplanner.event.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EventDto {

    private Long id;
    private boolean privateEvent;
    private Timestamp eventDate;
    private Timestamp starts;
    private Timestamp ends;
    private Long locationId;
}
