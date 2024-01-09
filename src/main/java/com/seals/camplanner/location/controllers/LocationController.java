package com.seals.camplanner.location.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seals.camplanner.location.dto.LocationDto;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<LocationDto> getLocations() {
        return toLocationDtoList(locationService.findAll());
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public LocationDto saveLocation(@RequestBody LocationDto locationDto) {
        return toDto(locationService.save(fromDto(locationDto)));
    }

    @GetMapping("/{id}")
    public LocationDto getLocation(@PathVariable("id") Long id) {
        return toDto(locationService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") Long id) {
        locationService.deleteById(id);
    }

    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocations() {
        locationService.deleteAll();
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
