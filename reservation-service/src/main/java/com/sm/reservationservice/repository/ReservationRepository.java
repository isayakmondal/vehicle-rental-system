package com.sm.reservationservice.repository;

import com.sm.reservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByVehicleId(String vehicleId);
}
