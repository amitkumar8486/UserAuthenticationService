package com.amit.ecommerce.authentication.user_authentication_service.controllers;

import com.amit.ecommerce.authentication.user_authentication_service.dtos.*;
import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.services.AuthenticationService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

        try {
            Pair<User, MultiValueMap<String,String>> bodyWithHeaders = authenticationService.login(loginDto.getEmail(),loginDto.getPassword());
            return new ResponseEntity<>(getUserDto(bodyWithHeaders.a),bodyWithHeaders.b, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody ValidateDto validateDto){
        return new ResponseEntity<>(authenticationService.validateToken(validateDto.getToken(),validateDto.getUserId()),HttpStatus.OK);

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
//        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
