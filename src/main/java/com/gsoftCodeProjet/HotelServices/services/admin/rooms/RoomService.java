package com.gsoftCodeProjet.HotelServices.services.admin.rooms;

import com.gsoftCodeProjet.HotelServices.dto.RoomDto;
import com.gsoftCodeProjet.HotelServices.dto.RoomsResponseDto;

public interface RoomService {

    boolean postRoom(RoomDto roomDto);
    RoomsResponseDto getAllRooms(int pageNumber);

    RoomDto getRoomsById(Long id);

    boolean updateRoom(Long id , RoomDto roomDto);

    void deleteRoom(Long id);
}
