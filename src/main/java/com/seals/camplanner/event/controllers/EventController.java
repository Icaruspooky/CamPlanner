package com.seals.camplanner.event.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.services.EventService;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    public EventController(EventService eventService, LocationService locationService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;

        Converter<Long, Location> converter = new AbstractConverter<>() {
            @Override
            protected Location convert(Long locationId) {
                return locationService.findById(locationId);
            }
        };
        this.modelMapper.addConverter(converter);
    }

    @GetMapping("/event")
    public List<EventDto> getEvents() {
        return toEventDtoList(eventService.findAll());
    }

    //TODO add treatment for the "location" parameter
    @PostMapping("/event")
    public EventDto saveEvent(@RequestBody EventDto event) {
        return toDto(eventService.save(fromDto(event)));
    }

    @GetMapping("/event/{id}")
    public EventDto getEvent(@PathVariable("id") Long id) {
        return toDto(eventService.findById(id));
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteById(id);
    }

    private EventDto toDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    private Event fromDto(EventDto eventDto) {
        return modelMapper.map(eventDto, Event.class);
    }

    private List<EventDto> toEventDtoList(List<Event> events) {
        Type type = new TypeReference<List<EventDto>>() {
        }.getType();
        return modelMapper.map(events, type);
    }
}
