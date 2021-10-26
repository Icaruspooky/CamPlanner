package com.seals.camplanner.event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class LocationUnavaliableException extends RuntimeException {
    public LocationUnavaliableException(Long id) {
        super(String.format("The id (%s) provided for the Location is not available.", id));
    }
}
