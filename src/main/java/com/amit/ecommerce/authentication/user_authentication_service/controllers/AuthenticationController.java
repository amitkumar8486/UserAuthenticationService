package com.amit.ecommerce.authentication.user_authentication_service.controllers;

import com.amit.ecommerce.authentication.user_authentication_service.dtos.LoginDto;
import com.amit.ecommerce.authentication.user_authentication_service.dtos.LogoutDto;
import com.amit.ecommerce.authentication.user_authentication_service.dtos.SignUpDto;
import com.amit.ecommerce.authentication.user_authentication_service.dtos.UserDto;
import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){

        try {
            User user = authenticationService.signUp(signUpDto.getEmail(), signUpDto.getPassword());
            return new ResponseEntity<>(getUserDto(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutDto logoutDto){
        return null;
    }

    /**
     * Converting User to UserDto
     * @param user
     * @return userDto
     */
    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
