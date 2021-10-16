package com.seals.camplanner.event.controllers;

import com.seals.camplanner.event.dto.EventDto;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.services.EventService;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final ModelMapper modelMapper;

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
        return toDto(eventService.find(id));
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(id);
    }

    private EventDto toDto(Event event) {
        EventDto eventDto = modelMapper.map(event, EventDto.class);
        eventDto.setLocationId(event.getLocation().getId());
        return eventDto;
    }

    private Event fromDto(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        Location location = locationService.find(eventDto.getLocationId());
        event.setLocation(location);
        return event;
    }

    private List<EventDto> toEventDtoList(List<Event> events) {
        return events.stream().map(event -> modelMapper.map(event, EventDto.class)).toList();
    }
}
