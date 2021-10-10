package com.seals.camplanner.event.controllers;

import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
