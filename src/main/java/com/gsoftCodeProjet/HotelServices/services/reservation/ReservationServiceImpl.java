package com.gsoftCodeProjet.HotelServices.services.reservation;

import com.gsoftCodeProjet.HotelServices.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
}
