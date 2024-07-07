package com.gsoftCodeProjet.HotelServices.controller.admin;

import com.gsoftCodeProjet.HotelServices.dto.RoomDto;
import com.gsoftCodeProjet.HotelServices.services.admin.rooms.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomService roomService;
    @PostMapping("/room")
    private ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto){
        boolean success = roomService.postRoom(roomDto);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
