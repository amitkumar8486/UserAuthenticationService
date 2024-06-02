package com.amit.ecommerce.authentication.user_authentication_service.dtos;

import com.amit.ecommerce.authentication.user_authentication_service.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateDto extends BaseModel {
    private String token;
    private Long userId;
}
