package com.gsoftCodeProjet.HotelServices.repository;

import com.gsoftCodeProjet.HotelServices.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long > {
}
