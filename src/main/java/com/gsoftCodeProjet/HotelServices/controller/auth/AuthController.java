package com.gsoftCodeProjet.HotelServices.controller.auth;

import com.gsoftCodeProjet.HotelServices.dto.AuthenticationRequest;
import com.gsoftCodeProjet.HotelServices.dto.AuthenticationResponse;
import com.gsoftCodeProjet.HotelServices.dto.SignupRequest;
import com.gsoftCodeProjet.HotelServices.dto.UserDto;
import com.gsoftCodeProjet.HotelServices.entity.User;
import com.gsoftCodeProjet.HotelServices.repository.UserRepository;
import com.gsoftCodeProjet.HotelServices.services.auth.AuthService;
import com.gsoftCodeProjet.HotelServices.services.jwt.UserService;
import com.gsoftCodeProjet.HotelServices.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final UserService userService;

//signup
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
    //login
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails= userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }

        return authenticationResponse;
    }



}
