package com.gsoftCodeProjet.HotelServices.services.admin.rooms;

import com.gsoftCodeProjet.HotelServices.dto.RoomDto;
import com.gsoftCodeProjet.HotelServices.dto.RoomsResponseDto;

public interface RoomService {

    boolean postRoom(RoomDto roomDto);
    RoomsResponseDto getAllRooms(int pageNumber);
}
