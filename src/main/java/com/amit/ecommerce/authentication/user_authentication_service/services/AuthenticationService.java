package com.amit.ecommerce.authentication.user_authentication_service.services;

import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User signUp(String email, String password) {
        Optional<User> userFound = userRepository.findByEmail(email);
        if(userFound.isPresent()){
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public User login(String email, String password) {
        return null;
    }
}
