package com.amit.ecommerce.authentication.user_authentication_service.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private String to;
    private String from;
    private String subject;
    private String body;
}
