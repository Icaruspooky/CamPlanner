package com.seals.camplanner.event.services;

import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    //TODO create customized exeption
    public Event find(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
