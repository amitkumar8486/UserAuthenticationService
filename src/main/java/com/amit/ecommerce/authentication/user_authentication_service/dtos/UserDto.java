package com.amit.ecommerce.authentication.user_authentication_service.dtos;

import com.amit.ecommerce.authentication.user_authentication_service.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role> roles = new HashSet<>();
}
