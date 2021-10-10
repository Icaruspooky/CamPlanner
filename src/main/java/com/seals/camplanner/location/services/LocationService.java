package com.seals.camplanner.location.services;

import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    //TODO create customized exeption
    public Location find(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
