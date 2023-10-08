package com.seals.camplanner.user.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.seals.camplanner.commons.services.BaseServiceImpl;
import com.seals.camplanner.user.models.User;
import com.seals.camplanner.user.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends BaseServiceImpl<User> {
    public static final String ENTITY_NAME = "User";
    private final UserRepository userRepository;

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return this.userRepository;
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }
}
