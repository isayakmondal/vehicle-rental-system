package com.sm.vehicleservice.service;

import com.sm.vehicleservice.model.Vehicle;

import java.util.List;

public interface VehicleService {

    Boolean add(Vehicle vehicle);
    Vehicle getVehicle(String id);

    List<Vehicle> getAllVehicle();

    Boolean updateVehicle(String id, Vehicle vehicle);

    Boolean deleteVehicle(String vehicleId);
}
