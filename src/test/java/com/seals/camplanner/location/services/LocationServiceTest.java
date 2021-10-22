package com.seals.camplanner.location.services;

import com.seals.camplanner.commons.services.BaseServiceImpl;
import com.seals.camplanner.commons.services.ServiceTestBase;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.repositories.LocationRepository;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Unit tests for the Location Service.
 *
 * <p>Since theses are unit tests there is no need to initialize the whole Spring Context, hence no @SpringBootTests
 * here. The repository is mocked so there will be no interactions with the database.</p>
 */
@ExtendWith(MockitoExtension.class)
public class LocationServiceTest extends ServiceTestBase<Location> {

    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService locationService;

    @Override
    protected BaseServiceImpl<Location> getService() {
        return this.locationService;
    }

    @Override
    protected JpaRepository<Location, Long> getRepositoryMock() {
        return this.locationRepository;
    }

    @Override
    protected Location getSample() {
        Location sampleLocation = new Location();
        sampleLocation.setId(RANDOM.nextLong());
        sampleLocation.setCity(UUID.randomUUID().toString());
        sampleLocation.setName(UUID.randomUUID().toString());
        sampleLocation.setCountry(UUID.randomUUID().toString());
        return sampleLocation;
    }
}
