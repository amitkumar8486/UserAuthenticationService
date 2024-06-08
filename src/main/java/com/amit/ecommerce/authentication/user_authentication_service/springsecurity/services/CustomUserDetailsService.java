package com.amit.ecommerce.authentication.user_authentication_service.springsecurity.services;

import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import com.amit.ecommerce.authentication.user_authentication_service.repositories.UserRepository;
import com.amit.ecommerce.authentication.user_authentication_service.springsecurity.models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("NOT FOUND");
        }

        return new CustomUserDetails(user.get());
    }
}
