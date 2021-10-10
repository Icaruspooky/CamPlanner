package com.seals.camplanner.event.controllers;

import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/event")
    public List<Event> getEvents() {
        return eventService.findAll();
    }

    @PostMapping("/event")
    public Event save(@RequestBody Event event) {
        return eventService.save(event);
    }
}
