package com.sm.vehicleservice.service.impl;

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
    public Boolean add(Vehicle vehicle) {
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
    public Boolean updateVehicle(String id, Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        if(vehicle!=null){
            if(updatedVehicle.getBrand()!=null) vehicle.setBrand(updatedVehicle.getBrand());
            if(updatedVehicle.getName()!=null) vehicle.setName(updatedVehicle.getName());
            vehicleRepository.save(vehicle);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteVehicle(String vehicleId) {
        if(getVehicle(vehicleId)!=null) {
            vehicleRepository.deleteById(vehicleId);
            return true;
        }
        return false;
    }
}
