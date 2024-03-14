package com.sm.vehicleservice.service.impl;

import com.sm.vehicleservice.dto.VehicleDTO;
import com.sm.vehicleservice.model.Vehicle;
import com.sm.vehicleservice.repository.VehicleRepository;
import com.sm.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Boolean add(VehicleDTO vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDto.getName());
        vehicle.setBrand(vehicleDto.getBrand());
        Vehicle addedVehicle = vehicleRepository.save(vehicle);
        return true;
    }

    @Override
    public Vehicle getVehicle(String id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        return vehicleList;
    }

    @Override
    public Boolean updateVehicle(String id, VehicleDTO vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null) {
            if (vehicleDto.getBrand() != null) vehicle.setBrand(vehicleDto.getBrand());
            if (vehicleDto.getName() != null) vehicle.setName(vehicleDto.getName());
            vehicleRepository.save(vehicle);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteVehicle(String vehicleId) {
        if (getVehicle(vehicleId) != null) {
            vehicleRepository.deleteById(vehicleId);
            return true;
        }
        return false;
    }
}
