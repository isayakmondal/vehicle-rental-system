package com.sm.reservationservice.controller;

import com.sm.reservationservice.model.Reservation;
import com.sm.reservationservice.service.ReservationService;
import com.sm.reservationservice.util.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservation(reservationId);
        if (reservation != null) {

            return ResponseEntity.status(HttpStatus.OK).body(reservation);

        }
        CustomResponse response = new CustomResponse("Reservation Not Found", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<?> addReservation(@Valid @RequestBody Reservation reservation) {
        if (reservationService.addReservation(reservation)) {

            CustomResponse response = new CustomResponse("Reservation Added Successfully", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        CustomResponse response = new CustomResponse("Reservation Not Added. Vehicle already reserved", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservation(){
        List<Reservation> reservationList = reservationService.getAllReservation();
        return ResponseEntity.status(HttpStatus.OK).body(reservationList);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody Reservation reservation){
        if(reservationService.updateReservation(reservationId, reservation)){
            CustomResponse response = new CustomResponse("Reservation Updated Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomResponse response = new CustomResponse("Reservation could not be updated", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId){
        if(reservationService.deleteReservation(reservationId)){
            CustomResponse response = new CustomResponse("Reservation Deleted Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        CustomResponse response = new CustomResponse("Reservation could not be deleted", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
