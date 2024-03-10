package com.sm.reservationservice.service;

import com.sm.reservationservice.model.Reservation;

import java.util.List;

public interface ReservationService {
    Boolean addReservation(Reservation reservation);
    Reservation getReservation(Long reservationId);
    List<Reservation> getAllReservation();
    Boolean updateReservation(Long reservationId, Reservation updatedReservation);
    Boolean deleteReservation(Long reservationId);
}
