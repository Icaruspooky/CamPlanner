package com.seals.camplanner.location.services;

import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.repositories.LocationRepository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for the Location Service.
 *
 * <p>Since theses are unit tests there is no need to initialize the whole Spring Context, hence no @SpringBootTests
 * here. The repository is mocked so there will be no interactions with the database.</p>
 */
@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    public static final Random RANDOM = new Random();

    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService locationService;

    @Test
    public void findByIdTest() {
        Location expected = this.getSampleLocation();
        Long id = expected.getId();
        Mockito.when(this.locationRepository.findById(id)).thenReturn(Optional.of(expected));
        Location actual = this.locationService.find(id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdNotFoundTest() {
        Long id = RANDOM.nextLong();
        Mockito.when(this.locationRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> this.locationService.find(id));
    }

    @Test
    public void findAllTest() {
        Location location1 = this.getSampleLocation();
        Location location2 = this.getSampleLocation();
        Location location3 = this.getSampleLocation();
        List<Location> expected = List.of(location1, location2, location3);
        Mockito.when(this.locationRepository.findAll()).thenReturn(expected);
        List<Location> actual = this.locationService.findAll();
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertTrue(actual.containsAll(expected));
    }

    @Test
    public void findAllEmptyTest() {
        List<Location> expected = List.of();
        Mockito.when(this.locationRepository.findAll()).thenReturn(expected);
        List<Location> actual = this.locationService.findAll();
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    public void saveTest() {
        Location toSave = this.getSampleLocation();
        toSave.setId(null);
        Mockito.when(this.locationRepository.save(toSave)).thenAnswer(invocation -> {
            Location saved = new Location();
            saved.setId(RANDOM.nextLong());
            saved.setName(toSave.getName());
            saved.setCountry(toSave.getCountry());
            saved.setCity(toSave.getCity());
            return saved;
        });
        Location saved = this.locationService.save(toSave);
        Mockito.verify(this.locationRepository).save(toSave);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(toSave.getName(), saved.getName());
        Assertions.assertEquals(toSave.getCountry(), saved.getCountry());
        Assertions.assertEquals(toSave.getCity(), saved.getCity());
    }

    @Test
    public void deleteByIdTest() {
        Long idToDelete = RANDOM.nextLong();
        this.locationService.deleteById(idToDelete);
        Mockito.verify(this.locationRepository).deleteById(idToDelete);
    }

    @Test
    public void deleteAllTest() {
        this.locationService.deleteAll();
        Mockito.verify(this.locationRepository).deleteAll();
    }

    public Location getSampleLocation() {
        Location sampleLocation = new Location();
        sampleLocation.setId(RANDOM.nextLong());
        sampleLocation.setCity(UUID.randomUUID().toString());
        sampleLocation.setName(UUID.randomUUID().toString());
        sampleLocation.setCountry(UUID.randomUUID().toString());
        return sampleLocation;
    }
}
