package com.seals.camplanner.event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LocationUnavaliableException extends RuntimeException {

    public static final String LOCATION_IS_UNAVAILABLE = "The id (%s) provided for the Location is not available.";

    public LocationUnavaliableException(Long id) {
        super(String.format(LOCATION_IS_UNAVAILABLE, id));
    }

    public LocationUnavaliableException(Long id, Throwable throwable) {
        super(String.format(LOCATION_IS_UNAVAILABLE, id), throwable);
    }
}
