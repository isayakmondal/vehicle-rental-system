package com.sm.reservationservice.service.impl;

import com.sm.reservationservice.dto.ReservationDTO;
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

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean addReservation(ReservationDTO reservationDto) {

        Reservation reservation = new Reservation();
        reservation.setReservedDateFrom(reservationDto.getReservedDateFrom());
        reservation.setReservedDateUpto(reservationDto.getReservedDateUpto());
        reservation.setRating(reservationDto.getRating());
        reservation.setReview(reservationDto.getReview());
        reservation.setVehicleId(reservationDto.getVehicleId());
        reservation.setCustomerId(reservationDto.getCustomerId());
        reservation.setVehicle(reservationDto.getVehicle());
        reservation.setCustomer(reservationDto.getCustomer());

        // [Optional] Check reservation based on from and upto dates
        if (reservationRepository.findByVehicleId(reservation.getVehicleId()) != null) return false;
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

        Customer customer = restTemplate.getForObject("http://customer-service:9093/customer/" + reservation.getCustomerId(), Customer.class);
        Vehicle vehicle = restTemplate.getForObject("http://vehicle-service:9091/vehicle/" + reservation.getVehicleId(), Vehicle.class);

        reservation.setCustomer(customer);
        reservation.setVehicle(vehicle);
        return reservation;

    }

    @Override
    public List<Reservation> getAllReservation() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<Reservation> updatedReservationList = reservationList.stream().map(reservation -> {

            Customer customer = restTemplate.getForObject("http://customer-service:9093/customer/" + reservation.getCustomerId(), Customer.class);
            Vehicle vehicle = restTemplate.getForObject("http://vehicle-service:9091/vehicle/" + reservation.getVehicleId(), Vehicle.class);

            reservation.setCustomer(customer);
            reservation.setVehicle(vehicle);
            return reservation;
        }).toList();
        return updatedReservationList;
    }

    @Override
    public Boolean updateReservation(Long reservationId, ReservationDTO reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            if (reservationDto.getIsReserved() != null) {
                reservation.setIsReserved(reservationDto.getIsReserved());
            }
            if (reservationDto.getReservedDateFrom() != null) {
                reservation.setReservedDateFrom(reservationDto.getReservedDateFrom());
            }
            if (reservationDto.getReservedDateUpto() != null) {
                reservation.setReservedDateUpto(reservationDto.getReservedDateUpto());
            }
            if (reservationDto.getRating() != null) {
                reservation.setRating(reservationDto.getRating());
            }
            if (reservationDto.getReview() != null) {
                reservation.setReview(reservationDto.getReview());
            }
            if (reservationDto.getVehicleId() != null) {
                reservation.setVehicleId(reservationDto.getVehicleId());
            }
            if (reservationDto.getCustomerId() != null) {
                reservation.setCustomerId(reservationDto.getCustomerId());
            }
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            reservationRepository.delete(reservation);
            return true;
        }
        return false;
    }
}
