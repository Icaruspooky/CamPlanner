package com.seals.camplanner.user.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seals.camplanner.user.dto.UserDto;
import com.seals.camplanner.user.models.User;
import com.seals.camplanner.user.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/user")
    public List<UserDto> getUsers() {
        return toUserDtoList(userService.findAll());
    }

    @PostMapping("/user/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return toDto(userService.save(fromDto(userDto)));
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return toDto(userService.findById(id));
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/user")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUsers() {
        userService.deleteAll();
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User fromDto(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private List<UserDto> toUserDtoList(List<User> users) {
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }
}
