package com.gsoftCodeProjet.HotelServices.services.customer.booking;

import com.gsoftCodeProjet.HotelServices.dto.ReservationDto;
import com.gsoftCodeProjet.HotelServices.entity.Reservation;
import com.gsoftCodeProjet.HotelServices.entity.Room;
import com.gsoftCodeProjet.HotelServices.entity.User;
import com.gsoftCodeProjet.HotelServices.enums.ReservationStatus;
import com.gsoftCodeProjet.HotelServices.repository.ReservationRepository;
import com.gsoftCodeProjet.HotelServices.repository.RoomRepository;
import com.gsoftCodeProjet.HotelServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto){
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if (optionalRoom.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice()*days);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
