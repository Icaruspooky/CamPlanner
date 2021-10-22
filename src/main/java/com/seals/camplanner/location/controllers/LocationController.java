package com.seals.camplanner.location.controllers;

import com.seals.camplanner.location.dto.LocationDto;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final ModelMapper modelMapper;

    @GetMapping("/location")
    public List<LocationDto> getLocations() {
        return toLocationDtoList(locationService.findAll());
    }

    @PostMapping("/location")
    public LocationDto saveLocation(@RequestBody LocationDto locationDto) {
        return toDto(locationService.save(fromDto(locationDto)));
    }

    @GetMapping("/location/{id}")
    public LocationDto getLocation(@PathVariable("id") Long id) {
        return toDto(locationService.findById(id));
    }

    @DeleteMapping("/location/{id}")
    public void deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteById(id);
    }

    private LocationDto toDto(Location location) {
        return modelMapper.map(location, LocationDto.class);
    }

    private Location fromDto(LocationDto locationDto) {
        return modelMapper.map(locationDto, Location.class);
    }

    private List<LocationDto> toLocationDtoList(List<Location> locations) {
        return locations.stream().map(location -> modelMapper.map(location, LocationDto.class)).toList();
    }
}
