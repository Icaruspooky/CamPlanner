package com.seals.camplanner.location.services;

import com.seals.camplanner.commons.services.BaseServiceImpl;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService extends BaseServiceImpl<Location> {

    private static final String ENTITY_NAME = "Location";

    private final LocationRepository locationRepository;

    @Override
    protected JpaRepository<Location, Long> getRepository() {
        return this.locationRepository;
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }
}
