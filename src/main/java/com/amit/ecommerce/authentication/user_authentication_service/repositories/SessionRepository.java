package com.amit.ecommerce.authentication.user_authentication_service.repositories;

import com.amit.ecommerce.authentication.user_authentication_service.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenAndUser_Id(String token, Long id);
}
