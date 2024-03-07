package com.sm.vehicleservice.controller;

import com.sm.vehicleservice.model.Vehicle;
import com.sm.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.add(vehicle);
        return new ResponseEntity<>("Vehicle Added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        List<Vehicle> vehicleList = vehicleService.getAllVehicle();
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String vehicleId){
        Vehicle vehicle = vehicleService.getVehicle(vehicleId);
        if(vehicle!=null){
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }
        // Implement Custom Error Message
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<String> updateVehicle(@PathVariable String vehicleId, @RequestBody Vehicle vehicle){
        Boolean isUpdated =  vehicleService.updateVehicle(vehicleId, vehicle);
        if(isUpdated) return new ResponseEntity<>("Vehicle Updated Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Vehicle Not Updated! ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId){
        Boolean isDeleted =  vehicleService.deleteVehicle(vehicleId);
        if(isDeleted) { return new ResponseEntity<>("Vehicle Deleted Successfully", HttpStatus.OK); }

        return new ResponseEntity<>("Vehicle Not Deleted! ", HttpStatus.NOT_FOUND);
    }

}
