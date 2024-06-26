package com.gsoftCodeProjet.HotelServices.dto;

import com.gsoftCodeProjet.HotelServices.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private UserRole userRole;
}
