package com.sm.vehicleservice.controller;

import com.sm.vehicleservice.dto.VehicleDTO;
import com.sm.vehicleservice.model.Vehicle;
import com.sm.vehicleservice.service.VehicleService;
import com.sm.vehicleservice.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody VehicleDTO vehicleDto) {
        vehicleService.add(vehicleDto);
        CustomResponse response = new CustomResponse("Vehicle Added Successfully", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle() {
        List<Vehicle> vehicleList = vehicleService.getAllVehicle();
        return ResponseEntity.status(HttpStatus.OK).body(vehicleList);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        if (vehicle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }
        // Implement Custom Response
        CustomResponse response = new CustomResponse("Vehicle Not Found!", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable String vehicleId, @RequestBody VehicleDTO vehicleDto) {
        Boolean isUpdated = vehicleService.updateVehicle(vehicleId, vehicleDto);
        if (isUpdated) {
            CustomResponse response = new CustomResponse("Vehicle Updated Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomResponse response = new CustomResponse("Vehicle Not Updated!", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        Boolean isDeleted = vehicleService.deleteVehicle(vehicleId);
        if (isDeleted) {
            CustomResponse response = new CustomResponse("Vehicle Deleted Successfully", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        CustomResponse response = new CustomResponse("Vehicle Not Deleted!", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
