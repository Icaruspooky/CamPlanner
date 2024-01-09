package com.seals.camplanner.event.controllers;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.seals.camplanner.commons.exceptions.NotFoundException;
import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.exceptions.LocationUnavaliableException;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.services.EventService;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;

@RestController
@RequestMapping("event")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    public EventController(EventService eventService, LocationService locationService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;

        Converter<Long, Location> converter = new AbstractConverter<>() {
            @Override
            protected Location convert(Long locationId) {
                Location location;
                try {
                    location = locationService.findById(locationId);
                } catch (NotFoundException e) {
                    throw new LocationUnavaliableException(locationId, e);
                }
                return location;
            }
        };
        this.modelMapper.addConverter(converter);
    }

    @GetMapping()
    public List<EventDto> getEvents() {
        return toEventDtoList(eventService.findAll());
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventDto saveEvent(@RequestBody EventDto eventDto) throws LocationUnavaliableException {
        Event event;
        try {
            event = fromDto(eventDto);
        } catch (MappingException e) {
            if (e.getCause() instanceof LocationUnavaliableException locationUnavaliableException) {
                throw locationUnavaliableException;
            }
            throw e;
        }
        return toDto(eventService.save(event));
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable("id") Long id) {
        return toDto(eventService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteById(id);
    }

    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEvents() {
        eventService.deleteAll();
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
