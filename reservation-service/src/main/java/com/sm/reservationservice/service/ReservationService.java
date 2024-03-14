package com.sm.reservationservice.service;

import com.sm.reservationservice.dto.ReservationDTO;
import com.sm.reservationservice.model.Reservation;

import java.util.List;

public interface ReservationService {
    Boolean addReservation(ReservationDTO reservationDto);
    Reservation getReservation(Long reservationId);
    List<Reservation> getAllReservation();
    Boolean updateReservation(Long reservationId, ReservationDTO reservationDto);
    Boolean deleteReservation(Long reservationId);
}
