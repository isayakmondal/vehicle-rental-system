package com.sm.reservationservice.dto;

import com.sm.reservationservice.external.model.Customer;
import com.sm.reservationservice.external.model.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    @Getter
    @Setter(AccessLevel.NONE)
    private Long id;
    @Getter
    @Setter(AccessLevel.NONE)
    private Boolean isReserved;
    private Date reservedDateFrom;
    private Date reservedDateUpto;
    private Double Rating;
    private String review;

    @NotBlank(message = "Vehicle ID is mandatory")
    private String vehicleId;
    @NotNull(message = "Customer ID is mandatory")
    private Long customerId;


    private Vehicle vehicle;
    private Customer customer;
}
