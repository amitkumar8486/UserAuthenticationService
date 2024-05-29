package com.amit.ecommerce.authentication.user_authentication_service.repositories;

import com.amit.ecommerce.authentication.user_authentication_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
