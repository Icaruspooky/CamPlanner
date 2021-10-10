package com.seals.camplanner.event.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDTO {

    private Long id;
    private boolean privateEvent;
    private Timestamp eventDate;
    private Timestamp starts;
    private Timestamp ends;
    private Long location;
}
