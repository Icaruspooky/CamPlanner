package com.seals.camplanner.event.controllers;

import com.seals.camplanner.commons.TestUtils;
import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class EventControllerTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void shouldMatchValuesMappingEventEventDto() {
        Event event = getSampleEvent();
        EventDto eventDto = modelMapper.map(event, EventDto.class);

        Assertions.assertEquals(event.getId(), eventDto.getId());
        Assertions.assertEquals(event.getEventDate(), eventDto.getEventDate());
        Assertions.assertEquals(event.getStarts(), eventDto.getStarts());
        Assertions.assertEquals(event.getEnds(), eventDto.getEnds());
        Assertions.assertEquals(event.getLocation().getId(), eventDto.getLocationId());
    }

    private Event getSampleEvent() {
        Event event = new Event();
        Instant now = Instant.now();
        Timestamp starts = Timestamp.from(now);
        Timestamp ends = Timestamp.from(now.plus(2, ChronoUnit.DAYS));
        event.setEventDate(starts);
        event.setStarts(starts);
        event.setEnds(ends);
        event.setLocation(TestUtils.getSampleLocation());
        return event;
    }

    //TODO create test from EventDto to Event
}
