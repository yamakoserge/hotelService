package com.gsoftCodeProjet.HotelServices.dto;

import com.gsoftCodeProjet.HotelServices.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
