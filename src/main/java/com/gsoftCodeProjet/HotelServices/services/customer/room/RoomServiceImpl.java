package com.gsoftCodeProjet.HotelServices.services.customer.room;

import com.gsoftCodeProjet.HotelServices.dto.RoomsResponseDto;
import com.gsoftCodeProjet.HotelServices.entity.Room;
import com.gsoftCodeProjet.HotelServices.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements  RoomService{

    private final RoomRepository roomRepository;

    public RoomsResponseDto getAvailableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber,4);
        Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPagesNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }
}
