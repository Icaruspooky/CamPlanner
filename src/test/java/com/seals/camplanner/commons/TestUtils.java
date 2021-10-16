package com.seals.camplanner.commons;

import com.seals.camplanner.location.dto.LocationDto;
import com.seals.camplanner.location.models.Location;

import java.util.Random;
import java.util.UUID;

public final class TestUtils {
    private TestUtils() {
    }

    public static Location getSampleLocation(Random random) {
        Location location = new Location();
        location.setId(random.nextLong());
        location.setName(UUID.randomUUID().toString());
        location.setCity(UUID.randomUUID().toString());
        location.setCountry(UUID.randomUUID().toString());
        return location;
    }

    public static LocationDto getSampleLocationDto(Random random) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(random.nextLong());
        locationDto.setName(UUID.randomUUID().toString());
        locationDto.setCity(UUID.randomUUID().toString());
        locationDto.setCountry(UUID.randomUUID().toString());
        return locationDto;
    }
}