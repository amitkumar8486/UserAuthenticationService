package com.amit.ecommerce.authentication.user_authentication_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Session extends BaseModel{
    private String token;

    @Enumerated(EnumType.ORDINAL)
    private SessionState sessionState;

    @ManyToOne
    private User user;
}
