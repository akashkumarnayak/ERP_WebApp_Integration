package org.integration.erp.controllers;

import org.integration.erp.dtos.UserRequestDto;
import org.integration.erp.dtos.UserResponseDto;
import org.integration.erp.entities.User;
import org.integration.erp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {

        User createdUser = userService.createUser(from(userRequestDto));
        return from(createdUser);

    }

    UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setAlias(user.getAlias());
        userResponseDto.setEmailId(user.getEmailId());
        return userResponseDto;
    }

    User from(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setAlias(userRequestDto.getAlias());
        user.setEmailId(userRequestDto.getEmailId());
        return user;
    }
}
