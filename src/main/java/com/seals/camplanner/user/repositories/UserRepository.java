package com.seals.camplanner.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seals.camplanner.user.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
