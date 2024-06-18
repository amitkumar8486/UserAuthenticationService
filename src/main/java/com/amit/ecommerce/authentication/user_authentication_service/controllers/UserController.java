package com.amit.ecommerce.authentication.user_authentication_service.controllers;

import com.amit.ecommerce.authentication.user_authentication_service.dtos.UserDto;
import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{uid}")
    public UserDto getUserById(@PathVariable Long uid){
        User userById = userService.getUserById(uid);
        return getUserDtoFromUser(userById);
    }

    private UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
