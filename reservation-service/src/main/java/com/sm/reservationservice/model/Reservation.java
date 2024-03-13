package com.sm.reservationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sm.reservationservice.external.model.Customer;
import com.sm.reservationservice.external.model.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isReserved;
    private Date reservedDateFrom;
    private Date reservedDateUpto;
    private Double Rating;
    private String review;

    @NotBlank(message = "This field is mandatory")
    private String vehicleId;
    @NotNull(message = "This field is mandatory")
    private Long customerId;

    @Transient
    private Vehicle vehicle;
    @Transient
    private Customer customer;
}
