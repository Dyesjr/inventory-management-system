package com.dyes.inventory_management_system.dto;

import com.dyes.inventory_management_system.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private Role role;
}
