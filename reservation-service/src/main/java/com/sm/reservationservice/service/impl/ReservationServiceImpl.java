package com.sm.reservationservice.service.impl;

import com.sm.reservationservice.external.model.Customer;
import com.sm.reservationservice.external.model.Vehicle;
import com.sm.reservationservice.model.Reservation;
import com.sm.reservationservice.repository.ReservationRepository;
import com.sm.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public Boolean addReservation(Reservation reservation) {
        // [Optional] Check reservation based on from and upto dates
        if(reservationRepository.findByVehicleId(reservation.getVehicleId())!=null) return false;
        try {
            reservation.setIsReserved(true);
            reservationRepository.save(reservation);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reservation getReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);

        Customer customer = restTemplate.getForObject("http://localhost:9093/customer/" + reservation.getCustomerId(), Customer.class);
        Vehicle vehicle = restTemplate.getForObject("http://localhost:9091/vehicle/" + reservation.getVehicleId(), Vehicle.class);

        reservation.setCustomer(customer);
        reservation.setVehicle(vehicle);
        return reservation;

    }

    @Override
    public List<Reservation> getAllReservation() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<Reservation> updatedReservationList = reservationList.stream().map(reservation -> {

            Customer customer = restTemplate.getForObject("http://localhost:9093/customer/" + reservation.getCustomerId(), Customer.class);
            Vehicle vehicle = restTemplate.getForObject("http://localhost:9091/vehicle/" + reservation.getVehicleId(), Vehicle.class);

            reservation.setCustomer(customer);
            reservation.setVehicle(vehicle);
            return reservation;
        }).toList();
        return updatedReservationList;
    }

    @Override
    public Boolean updateReservation(Long reservationId, Reservation updatedReservation) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if(reservation!=null){
            if(updatedReservation.getIsReserved()!=null) { reservation.setIsReserved(updatedReservation.getIsReserved()); }
            if(updatedReservation.getReservedDateFrom()!=null) { reservation.setReservedDateFrom(updatedReservation.getReservedDateFrom()); }
            if(updatedReservation.getReservedDateUpto()!=null) { reservation.setReservedDateUpto(updatedReservation.getReservedDateUpto()); }
            if(updatedReservation.getRating()!=null) { reservation.setRating(updatedReservation.getRating()); }
            if(updatedReservation.getReview()!=null) { reservation.setReview(updatedReservation.getReview()); }
            if(updatedReservation.getVehicleId()!=null) { reservation.setVehicleId(updatedReservation.getVehicleId()); }
            if(updatedReservation.getCustomerId()!=null) { reservation.setCustomerId(updatedReservation.getCustomerId()); }
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if(reservation!=null){
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }
}
