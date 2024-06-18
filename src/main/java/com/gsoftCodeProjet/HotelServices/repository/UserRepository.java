package com.gsoftCodeProjet.HotelServices.repository;

import com.gsoftCodeProjet.HotelServices.entity.User;
import com.gsoftCodeProjet.HotelServices.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
