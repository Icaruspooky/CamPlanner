package com.seals.camplanner.event.controllers;

import com.seals.camplanner.commons.TestUtils;
import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class EventControllerTest {

    @Autowired
    private Random random;

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
        Timestamp starts = Timestamp.from(Instant.ofEpochSecond(random.nextLong()));
        Timestamp ends = Timestamp.valueOf(starts.toLocalDateTime().plusDays(2));
        event.setEventDate(starts);
        event.setStarts(starts);
        event.setEnds(ends);
        event.setLocation(TestUtils.getSampleLocation(random));
        return event;
    }

    //TODO create test from EventDto to Event
}
