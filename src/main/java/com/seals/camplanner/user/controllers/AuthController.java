package com.seals.camplanner.user.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seals.camplanner.commons.config.security.TokenService;
import com.seals.camplanner.user.dto.UserLoginDto;
import com.seals.camplanner.user.models.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public String login(@RequestBody UserLoginDto userLoginDto) {
        var token = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
        var authentication = authenticationManager.authenticate(token);

        return tokenService.generateToken((User) authentication.getPrincipal());
    }
}
