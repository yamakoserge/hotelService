package com.gsoftCodeProjet.HotelServices.repository;

import com.gsoftCodeProjet.HotelServices.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
