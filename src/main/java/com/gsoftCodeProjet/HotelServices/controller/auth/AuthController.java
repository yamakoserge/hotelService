package com.gsoftCodeProjet.HotelServices.controller.auth;

import com.gsoftCodeProjet.HotelServices.dto.SignupRequest;
import com.gsoftCodeProjet.HotelServices.dto.UserDto;
import com.gsoftCodeProjet.HotelServices.services.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("Le nom existe déjà", HttpStatus.NOT_ACCEPTABLE);

        }catch (Exception e){
            return new ResponseEntity<>("L'Utilisation n'a pas été créé, Essayez Encore!!!", HttpStatus.BAD_REQUEST);

        }

    }
}
