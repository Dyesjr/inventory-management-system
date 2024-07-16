package com.dyes.inventory_management_system.service.query;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long expiresIn;
}
