package com.seals.camplanner.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seals.camplanner.user.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
