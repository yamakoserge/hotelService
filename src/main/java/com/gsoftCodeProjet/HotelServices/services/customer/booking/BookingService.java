package com.gsoftCodeProjet.HotelServices.services.customer.booking;

import com.gsoftCodeProjet.HotelServices.dto.ReservationDto;

public interface BookingService {

    boolean postReservation(ReservationDto reservationDto);
}
