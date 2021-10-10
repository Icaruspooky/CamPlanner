package com.seals.camplanner.location.controllers;

import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/location")
    public List<Location> getLocations() {
        return locationService.findAll();
    }

    @PostMapping("/event")
    public Location saveEvent(@RequestBody Location location) {
        return locationService.save(location);
    }

    @GetMapping("/event/{id}")
    public Location getEvent(@PathVariable("id") Long id) {
        return locationService.find(id);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        locationService.delete(id);
    }
}
