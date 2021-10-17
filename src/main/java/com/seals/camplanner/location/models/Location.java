package com.seals.camplanner.location.models;

import com.seals.camplanner.commons.models.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    //TODO implement maps API
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;
}
