package com.sm.vehicleservice.service;

import com.sm.vehicleservice.dto.VehicleDTO;
import com.sm.vehicleservice.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Boolean add(VehicleDTO vehicleDto);
    Vehicle getVehicle(String id);

    List<Vehicle> getAllVehicle();

    Boolean updateVehicle(String id, VehicleDTO vehicleDto);

    Boolean deleteVehicle(String vehicleId);
}
