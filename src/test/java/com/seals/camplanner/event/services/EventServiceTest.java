package com.seals.camplanner.event.services;

import com.seals.camplanner.commons.services.BaseServiceImpl;
import com.seals.camplanner.commons.services.ServiceTestBase;
import com.seals.camplanner.event.EventTestUtils;
import com.seals.camplanner.event.models.Event;
import com.seals.camplanner.event.repositories.EventRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest extends ServiceTestBase<Event> {

    @Mock
    private EventRepository repository;
    @InjectMocks
    private EventService eventService;

    @Override
    protected BaseServiceImpl<Event> getService() {
        return this.eventService;
    }

    @Override
    protected JpaRepository<Event, Long> getRepositoryMock() {
        return this.repository;
    }

    @Override
    protected Event getSample() {
        return EventTestUtils.getSampleEvent();
    }
}
