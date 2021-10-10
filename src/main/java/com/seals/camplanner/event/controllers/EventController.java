package com.seals.camplanner.event.controllers;

import com.seals.camplanner.event.dto.EventDTO;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/event")
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    //TODO add treatment for the "location" parameter
    @PostMapping("/event")
    public Event saveEvent(@RequestBody EventDTO event) {
        return eventService.save(event);
    }

    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable("id") Long id) {
        return eventService.find(id);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(id);
    }
}
