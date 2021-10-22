package com.seals.camplanner.commons;

import com.seals.camplanner.location.dto.LocationDto;
import com.seals.camplanner.location.models.Location;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public final class TestUtils {
    public static final Random RANDOM = new Random();

    public static Location getSampleLocation() {
        Location location = new Location();
        location.setId(RANDOM.nextLong());
        location.setName(UUID.randomUUID().toString());
        location.setCity(UUID.randomUUID().toString());
        location.setCountry(UUID.randomUUID().toString());
        return location;
    }

    public static LocationDto getSampleLocationDto() {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(RANDOM.nextLong());
        locationDto.setName(UUID.randomUUID().toString());
        locationDto.setCity(UUID.randomUUID().toString());
        locationDto.setCountry(UUID.randomUUID().toString());
        return locationDto;
    }
}
