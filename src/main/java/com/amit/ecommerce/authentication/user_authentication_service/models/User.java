package com.amit.ecommerce.authentication.user_authentication_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    /* For M:M a separate mapping table will be created.*/
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
