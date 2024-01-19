package com.seals.camplanner.event.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.seals.camplanner.commons.services.BaseServiceImpl;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.repositories.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService extends BaseServiceImpl<Event> {
    public static final String ENTITY_NAME = "Event";
    private final EventRepository eventRepository;

    @Override
    protected JpaRepository<Event, Long> getRepository() {
        return this.eventRepository;
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }
}
