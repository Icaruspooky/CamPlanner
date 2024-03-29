package com.seals.camplanner.location.controllers;

import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
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
    public Page<LocationDto> getLocations(Pageable pageable) {
        return toLocationDtoList(locationService.findAll(pageable));
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

    private Page<LocationDto> toLocationDtoList(Page<Location> locations) {
        Type type = new TypeReference<Page<LocationDto>>() {
        }.getType();
        return modelMapper.map(locations, type);
    }
}
