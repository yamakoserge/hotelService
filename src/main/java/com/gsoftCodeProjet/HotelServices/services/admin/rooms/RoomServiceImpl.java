package com.gsoftCodeProjet.HotelServices.services.admin.rooms;

import com.gsoftCodeProjet.HotelServices.dto.RoomDto;
import com.gsoftCodeProjet.HotelServices.entity.Room;
import com.gsoftCodeProjet.HotelServices.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl  implements RoomService{

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto){
        try{
            Room room = new Room();

            room.setName(room.getName());
            room.setType(room.getType());
            room.setPrice(room.getPrice());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
