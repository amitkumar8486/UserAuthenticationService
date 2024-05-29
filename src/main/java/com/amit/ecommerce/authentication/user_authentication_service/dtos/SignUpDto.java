package com.amit.ecommerce.authentication.user_authentication_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String email;
    private String password;
}
