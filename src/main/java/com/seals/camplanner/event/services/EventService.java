package com.seals.camplanner.event.services;

import com.seals.camplanner.event.dto.EventDTO;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.repositories.EventRepository;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final LocationService locationService;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event save(EventDTO event) {
        Event newEvent = new Event();
        newEvent.setPrivateEvent(event.isPrivateEvent());
        newEvent.setEventDate(event.getEventDate());
        newEvent.setStarts(event.getStarts());
        newEvent.setEnds(event.getEnds());
        Location location = locationService.find(event.getLocation());
        newEvent.setLocation(location);
        return eventRepository.save(newEvent);
    }

    //TODO create customized exeption
    public Event find(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
