package com.gsoftCodeProjet.HotelServices.services.auth;

import com.gsoftCodeProjet.HotelServices.dto.SignupRequest;
import com.gsoftCodeProjet.HotelServices.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
