package com.gsoftCodeProjet.HotelServices.services.auth;

import com.gsoftCodeProjet.HotelServices.dto.SignupRequest;
import com.gsoftCodeProjet.HotelServices.dto.UserDto;
import com.gsoftCodeProjet.HotelServices.entity.User;
import com.gsoftCodeProjet.HotelServices.enums.UserRole;
import com.gsoftCodeProjet.HotelServices.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void creatAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Le Compte Administrateur a été avec success");
        }else {
            System.out.println("Le Compte Administrateur existe déjâ !");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With email" + signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMERS);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createUser = userRepository.save(user);
        return createUser.getUserDto();


    }
}
