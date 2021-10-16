package com.seals.camplanner.location.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seals.camplanner.location.models.Location;
import com.seals.camplanner.location.services.LocationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration Tests for the Location endpoint. Uses MockMvc do simulate endpoint calls.
 *
 * <p>Since these are integration tests no component is being mocked and the tests will interact with the database.
 * The tests are transactional and should rollback by default, but it is still recommended to run with an in
 * memory database like h2.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Random random;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LocationService locationService;

    @Test
    public void findAllWhenEmptyTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/location"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.empty()));
    }

    @Test
    public void saveLocationTest() throws Exception {
        Location location = this.getSampleLocation();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                                                                      .contentType(MediaType.APPLICATION_JSON)
                                                                      .content(
                                                                              this.mapper.writeValueAsString(location));
        this.mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.any(Number.class)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.city", Matchers.is(location.getCity())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.country", Matchers.is(location.getCountry())))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(location.getName())));
    }

    @Test
    public void findAllTest() throws Exception {
        List<Location> expected = new ArrayList<>();
        expected.add(this.locationService.save(this.getSampleLocation()));
        expected.add(this.locationService.save(this.getSampleLocation()));
        expected.add(this.locationService.save(this.getSampleLocation()));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/location"))
                                       .andExpect(MockMvcResultMatchers.status().isOk())
                                       .andExpect(MockMvcResultMatchers.content()
                                                                       .contentType(MediaType.APPLICATION_JSON))
                                       .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                                       .andReturn();
        TypeReference<List<Location>> type = new TypeReference<>() {
        };
        List<Location> actual = this.mapper.readValue(result.getResponse().getContentAsString(), type);
        Assertions.assertEquals(3, expected.size());
        Assertions.assertTrue(actual.containsAll(expected));
    }

    @Test
    public void findByIdTest() throws Exception {
        Location expected = this.locationService.save(this.getSampleLocation());

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/location/{id}", expected.getId()))
                                       .andExpect(MockMvcResultMatchers.status().isOk())
                                       .andExpect(MockMvcResultMatchers.content()
                                                                       .contentType(MediaType.APPLICATION_JSON))
                                       .andReturn();
        Location actual = this.mapper.readValue(result.getResponse().getContentAsString(), Location.class);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void deleteByIdTest() throws Exception {
        Location location = this.locationService.save(this.getSampleLocation());

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/location/{id}", location.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertTrue(this.locationService.findAll().isEmpty());
    }

    private Location getSampleLocation() {
        Location location = new Location();
        location.setId(this.random.nextLong());
        location.setName(UUID.randomUUID().toString());
        location.setCity(UUID.randomUUID().toString());
        location.setCountry(UUID.randomUUID().toString());
        return location;
    }
}