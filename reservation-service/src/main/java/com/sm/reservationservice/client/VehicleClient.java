package com.sm.reservationservice.client;

import com.sm.reservationservice.external.model.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("vehicle-service")
public interface VehicleClient {
    @GetMapping("/vehicle/{vehicleId}")
    Vehicle getVehicle(@PathVariable String vehicleId);
}
