package com.seals.camplanner.event.controllers;

import com.seals.camplanner.event.EventTestUtils;
import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class EventControllerTest {

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    void shouldMatchValuesMappingEventEventDto() {
        Event event = EventTestUtils.getSampleEvent();
        EventDto eventDto = modelMapper.map(event, EventDto.class);

        Assertions.assertEquals(event.getId(), eventDto.getId());
        Assertions.assertEquals(event.getEventDate(), eventDto.getEventDate());
        Assertions.assertEquals(event.getStarts(), eventDto.getStarts());
        Assertions.assertEquals(event.getEnds(), eventDto.getEnds());
        Assertions.assertEquals(event.getLocation().getId(), eventDto.getLocationId());
    }

    //TODO create test from EventDto to Event
}
