package com.amit.ecommerce.authentication.user_authentication_service.services;

import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id){
        return userRepository.findByIdEquals(id).get();
    }
}
