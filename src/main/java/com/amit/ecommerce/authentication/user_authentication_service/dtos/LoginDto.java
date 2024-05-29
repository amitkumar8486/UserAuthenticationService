package com.amit.ecommerce.authentication.user_authentication_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDto {
    private String email;
    private String password;
}
