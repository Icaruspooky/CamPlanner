package com.seals.camplanner.user.models;

import com.seals.camplanner.commons.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    // Other fields and relationships
    // Implement UserDetails methods and other required methods
    // Getters and setters
}
